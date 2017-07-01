package com.wetool.push.api.model.server.push;

import com.wetool.push.api.model.PushMsgType;
import com.wetool.push.api.model.server.PushMessage;
import lombok.Getter;

/**
 * Created by zhanbin on 7/1/17.
 * server --> client
 * 同步上传图片请求
 */
@Getter
public class ImageSyncMessage extends PushMessage {

    private static final long serialVersionUID = 6496471882444291625L;

    private String imageId;
    private String sn;

    public ImageSyncMessage(String imageId, String sn) {
        super(PushMsgType.QRCODE_IMAGE_SYNC);
        this.imageId = imageId;
        this.sn = sn;
    }
}
