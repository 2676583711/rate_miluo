var client = {};
var uid = 'user_'+parseInt(Math.random()*10000);
$(function () {
    $("#join").on('click',function () {
//
//         创建 Client 对象,通过TRTC.createClient()方法创建Client对象，参数设置：
//         1: mode: 实时音视频通话模式，设置为‘rtc’; 2: sdkAppId: 您从腾讯云申请的 sdkAppId
//         3: userId: 用户 ID ;                     4: userSig: 用户签名

        var userSig = genTestUserSig(uid).userSig;
        var sdkAppId = genTestUserSig(uid).sdkAppId;

        client = TRTC.createClient({
            mode: 'videoCall',
            sdkAppId: sdkAppId,
            userId: uid,
            userSig: userSig,

        });

//         // 订阅远端音视频流
//         // 远端流通过监听事件client.on('stream-added')获得，请在join()进房前注册该事件，
//         // 确保您不会错过远端用户进房通知。 收到上述事件后要通过subscribe()订阅远端音视频流
        client.on('stream-added', function (event) {
            var remoteStream = event.stream;
            console.log('远端流增加: ' + remoteStream.getId());

            //订阅远端流
            client.subscribe(remoteStream);
        });


        var roomId = 8888;
        client.join({roomId: roomId}).catch(function (err)   {
            console.log('进房失败 ' + err);
        }).then(function ()   {
            console.log('进房成功');
            // 这里是去创建本地流
            initLocalStream();
        });
    });

});

function initLocalStream() {
    var localStream = TRTC.createStream({userId: uid, audio: true, video: true});

    localStream.initialize().then(function () {
        console.log('初始化本地流成功');

        localStream.play('local_stream');

        client.publish(localStream).catch(function (reason) {
            console.error('本地流发布失败： ' + reason)
        }).then(function () {
                console.log('本地流发布成功')
            }
        )
    }).catch(function (err) {
        console.error('初始化本地流失败 ' + err);

    });

    client.on('stream-subscribed', function (event){
        var remoteStream = event.stream;
    console.log('远端流订阅成功：' + remoteStream.getId());

    // 播放远端流
    remoteStream.play('remote_stream-' + remoteStream.getId());
});

}

function genTestUserSig(userID) {
    /**
     * 腾讯云 SDKAppId，需要替换为您自己账号下的 SDKAppId。
     *
     * 进入腾讯云实时音视频[控制台](https://console.cloud.tencent.com/rav ) 创建应用，即可看到 SDKAppId，
     * 它是腾讯云用于区分客户的唯一标识。
     */
    var SDKAPPID = 1400397256;

    /**
     * 签名过期时间，建议不要设置的过短
     * <p>
     * 时间单位：秒
     * 默认时间：7 x 24 x 60 x 60 = 604800 = 7 天
     */
    var EXPIRETIME = 604800;

    /**
     * 计算签名用的加密密钥，获取步骤如下：
     *
     * step1. 进入腾讯云实时音视频[控制台](https://console.cloud.tencent.com/rav )，如果还没有应用就创建一个，
     * step2. 单击“应用配置”进入基础配置页面，并进一步找到“帐号体系集成”部分。
     * step3. 点击“查看密钥”按钮，就可以看到计算 UserSig 使用的加密的密钥了，请将其拷贝并复制到如下的变量中
     *
     * 注意：该方案仅适用于调试Demo，正式上线前请将 UserSig 计算代码和密钥迁移到您的后台服务器上，以避免加密密钥泄露导致的流量盗用。
     * 文档：https://cloud.tencent.com/document/product/647/17275#Server
     */
    var SECRETKEY = '53cd463854afee5a5494fa0faaba2b303391af328beea7fc6397e7cc7d5cf738';

    // a soft reminder to guide developer to configure sdkAppId/secretKey
    if (SDKAPPID === '' || SECRETKEY === '') {
        alert(
            '请先配置好您的账号信息： SDKAPPID 及 SECRETKEY ' +
            '\r\n\r\nPlease configure your SDKAPPID/SECRETKEY in js/debug/GenerateTestUserSig.js'
        );
    }
    var generator = new LibGenerateTestUserSig(SDKAPPID, SECRETKEY, EXPIRETIME);
    var userSig = generator.genTestUserSig(userID);
    return {
        sdkAppId: SDKAPPID,
        userSig: userSig
    };
}
