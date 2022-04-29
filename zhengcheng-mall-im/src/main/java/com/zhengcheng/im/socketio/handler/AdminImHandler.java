package com.zhengcheng.im.socketio.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.zhengcheng.mall.domain.entity.Message;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台即时通信处理器
 *
 * @author :    zhangquansheng
 * @date :    2019/12/20 15:17
 */
@Slf4j
@Component
public class AdminImHandler {

    /**
     * 后台管理即时通讯通道
     */
    private final String                  ADMIN_IM_NAMESPACE      = "/admin/im";

    /**
     * SocketIO 表示当前用户的参数
     */
    private final String                  HANDSHAKE_DATA_PARAM_ID = "id";

    /**
     * 后台管理即时通讯消息事件名称
     */
    private final String                  ADMIN_IM_MESSAGE_EVENT  = "adminImMessageEvent";

    private final SocketIONamespace       namespace;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public AdminImHandler(SocketIOServer server) {
        namespace = server.addNamespace(ADMIN_IM_NAMESPACE);
        namespace.addConnectListener(onConnected());
        namespace.addDisconnectListener(onDisconnected());
    }

    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            log.info("Client[{}] - Connected to chat module through '{}'", client.getSessionId(),
                    handshakeData.getUrl());
            String id = client.getHandshakeData().getSingleUrlParam(HANDSHAKE_DATA_PARAM_ID);
            if (StrUtil.isNotBlank(id) && NumberUtil.isInteger(id)) {
                Long userId = Long.valueOf(id);
                redisTemplate.opsForSet().add(this.getConnectedSessionCacheKey(userId), client.getSessionId());
            }
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from chat module.", client.getSessionId());
            String id = client.getHandshakeData().getSingleUrlParam(HANDSHAKE_DATA_PARAM_ID);
            if (StrUtil.isNotBlank(id) && NumberUtil.isInteger(id)) {
                Long userId = Long.valueOf(id);
                redisTemplate.opsForSet().remove(this.getConnectedSessionCacheKey(userId), client.getSessionId());
            }
        };
    }

    /**
     * 获取会员链接的会话缓存KEY
     */
    private String getConnectedSessionCacheKey(Long id) {
        return "zc:im:connected:session:".concat(String.valueOf(id));
    }

    /**
     * 发送消息
     * @param message 消息
     */
    public boolean sendMessage(Message message) {
        List<SocketIOClient> socketIOClientList = this.getSocketIOClient(message.getReceiverId());
        if (CollectionUtil.isNotEmpty(socketIOClientList)) {
            for (SocketIOClient socketIOClient : socketIOClientList) {
                socketIOClient.sendEvent(ADMIN_IM_MESSAGE_EVENT, message);
            }
            return true;
        }
        return false;
    }

    /**
     * 获取用户的 SocketIOClient
     *
     * @param userId 用户
     * @return 客户端
     */
    private List<SocketIOClient> getSocketIOClient(Long userId) {
        Set<Object> uuidSet = redisTemplate.opsForSet().members(this.getConnectedSessionCacheKey(userId));
        List<SocketIOClient> socketIOClientList = new ArrayList<>();
        for (Object uuid : uuidSet) {
            if (uuid == null) {
                continue;
            }
            SocketIOClient socketIOClient = namespace.getClient((UUID) uuid);
            if (socketIOClient != null) {
                socketIOClientList.add(socketIOClient);
            }
        }
        return socketIOClientList;
    }
}
