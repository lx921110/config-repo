package com.wetool.push.api.model.server.push;

import com.wetool.push.api.model.PushMsgType;
import com.wetool.push.api.model.server.PushMessage;
import lombok.Getter;

/**
 * Created by zhanbin on 7/1/17.
 * server --> client
 * 同步上传图片请求，用于同步二维码扫码上传的推送消息类型。
 */
@Getter
public class ImageSyncMessage extends PushMessage {

    private static final long serialVersionUID = 6496471882444291625L;

    /**
     * 图片位于阿里OSS的唯一ID(路径+图片名称)
     */
    private String imageId;


    public ImageSyncMessage(String imageId, String sn) {
        super(PushMsgType.QRCODE_IMAGE_SYNC, sn);
        this.imageId = imageId;
    }
}
