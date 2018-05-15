var user = new Object();
var imgUrl = "";
/*界面初始化函数*/
$(function layoutRender() {
    //正在加载
    $(".cloud-load-more__button").html("正&nbsp;在&nbsp;加&nbsp;载...");
    $(".cloud-load-more__button").attr("disabled", "true");
    /*界面初始化渲染函数*/
    var h = $(window).outerHeight();
    var w = $(window).outerWidth();
    if (w < 835) {
        /*窗口小于手机限定值，重新放置面板位置*/
        var about_panel = $(".cloud-page-about");
        $(".cloud-dashboard-left").append($(".cloud-page-about"));
    } else {
        $(".cloud-dashboard-right").append($(".cloud-page-about"));
    }
    $(window).resize(function () {
        /*窗口大小改变重绘面板*/
        var w = $(window).outerWidth();
        if (w < 840) {
            /*窗口小于手机限定值，重新放置面板位置*/
            var about_panel = $(".cloud-page-about");
            $(".cloud-dashboard-left").append($(".cloud-page-about"));
        } else {
            $(".cloud-dashboard-right").append($(".cloud-page-about"));
        }
        ;
    });

    //emoji
    // $(".moment-box__input").emojioneArea({
    //     pickerPosition: "bottom",
    //     tonesStyle: "bullet"
    // });

    /************************************webuploader组件初始化begin*******************************/
    var uploader = WebUploader.create({
        auto: true,
        // swf文件路径
        swf: '../webuploader-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server: 'upload.do',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#moment-box__button-photo',

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: true,
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {

        var list = $('.moment-box__preview');

        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img>' + '</div>'
            ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        list.append($li);

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr('src', src);
        }, 100, 100);
    });

// 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });

    uploader.on('uploadSuccess', function (file, response) {
        $('#' + file.id).find('p.state').text('已上传');
        imgUrl = imgUrl + response._raw + ",";
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });
    /************************************webuploader组件初始化end*******************************/

    /**********************************发推特点击事件begin******************************************************/
    $("#moment-box__button-publish").click(function () {
        $.ajax({
            url: "create.do",
            type: "POST",
            dataType: "JSON",
            data: {
                type: "twitter",
                twitterContent: $('.moment-box__input').val(),
                imgUrl: imgUrl
            }, success: function (data, status) {
                fillMomentItem(data, "totop");
            },
            error: function (e) {
                alert("请检查网络连接");
            }
        })
        $('.moment-box__input').val("");
        $('.moment-box__preview').html("");
        imgUrl = "";
    })

    /**********************************发推特点击事件end******************************************************/
})

$(function bindEvent() {
    $('.cloud-navigation-avator').click(function () {
        window.open("avator.html");
    })
    $(".cloud-load-more__button").click(function () {
        $(".cloud-load-more__button").html("正&nbsp;在&nbsp;加&nbsp;载...");
        $(".cloud-load-more__button").attr("disabled", "true");
        var lastId = $(".timeline-stream>.timeline-stream-item:last").attr("twitter-id");
        $.ajax({
            url: "findTwitter.do",
            type: "post",
            dataType: "json",
            data: {
                type: "homeTwitter",
                lastId: lastId,
                rows: 10
            },
            success: function (data, textStatus) {
                if (data.length == 0) {
                    $(".cloud-load-more__button").html("没&nbsp;有&nbsp;更&nbsp;多");
                } else {
                    $(".cloud-load-more__button").html("加&nbsp;载&nbsp;更&nbsp;多");
                    $(".cloud-load-more__button").removeAttr("disabled");
                }
                for (var i = 0; i < data.length; i++) {
                    fillMomentItem(data[i]);
                }
            }
        });
    })
})

$(function init() {
    /* 初始化用户信息 */
    $.ajax({
        url: "getInfo.do",
        type: "POST",
        // async:false,
        dataType: "json",
        data: {
            type: "loginUser"
        }, success: function (data, textStatus) {
            $(".cloud-login-avator__img").attr("src", data.avator + "?t=" + Math.random());
            user.userName = data.userName;
            user.userId = data.userAccount;
            user.avator = data.avator;
            $('.dashboard_profile-userinfo__name').html(data.userName);
            $('.dashboard_profile-userinfo__id').html(data.userAccount);
            $('.dashboard_profile-state-moments-num').html(data.twitterNum);
            $('.dashboard_profile-state-following-num').html(data.starNum);
            $('.dashboard_profile-state-follower-num').html(data.follwNum);
        },
        error: function (e) {
            alert("请检查网络连接");
        }
    });
    getMoment(10);
    $(".cloud-load-more__button").html("加&nbsp;载&nbsp;更&nbsp;多");
    $(".cloud-load-more__button").removeAttr("disabled");
})

function getMoment(number) {
    /*获取所有的动态信息*/
    $.ajax({
        url: "findTwitter.do",
        type: "post",
        dataType: "json",
        data: {
            type: "homeTwitter",
            lastId: "",
            rows: number
        },
        success: function (data, textStatus) {
            for (var i = 0; i < data.length; i++) {
                fillMomentItem(data[i]);
            }
        }
    });
}

function fillMomentItem(momentData, direction) {
    /* totop:自上向下添加 */
    /*填充动态信息函数，每次只填充一个*/
    var moment_template = $('<li class="timeline-stream-item"><div class="cloud-moment"><div class="cloud-moment-avator"><img class="cloud-moment-avator__img" src="" width="48px" height="48px" alt="avator" /></div><div class="cloud-monent-content"><div class="monent-content-container cloud-moment-content__header"><span class="moment-user-name-container"><strong class="moment-user-name"></strong></span><span class="moment-content__split"></span><span class="moment-user-id-container"><span>@</span><b class="moment-user-id"></b></span><span class="moment-content__split"></span><span class="moment-time-container"><span class="moment-time"></span></span><div class="moment-menu"><button id="" class="moment-menu__button mdl-button mdl-js-button mdl-button--icon"><i class="material-icons">more_vert</i></button><ul class="moment-menu__button__area mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for=""><li class="moment-menu__button-block mdl-menu__item">屏蔽该条动态</li><li class="moment-menu__button-report mdl-menu__item">举报动态</li><li class="moment-menu__button-delete mdl-menu__item">删除动态</li></ul></div></div><div class="monent-content-container cloud-moment-content__text"></div><div class="monent-content-container cloud-moment-content__media"></div><div class="monent-content-container cloud-moment-content__footer"><div class="monent-content-container cloud-moment-action-container"><div class="moment-action__comment cloud-moment-action"><button class="moment-action-button moment-action-button__comment"><i class="material-icons">comment</i></button><span class="action-badage__comment-num cloud-moment-action__badge"></span></div><div class="moment-action__transmit cloud-moment-action"><button class="moment-action-button moment-action-button__transmit"><i class="material-icons">cached</i></button><span class="action-badage__transmit-num cloud-moment-action__badge"></span></div><div class="moment-action__favorite cloud-moment-action"><button class="moment-action-button moment-action-button__favorite"><i class="material-icons">favorite_border</i></button><span class="action-badage__favorite-num cloud-moment-action__badge"></span></div><div class="moment-action__message cloud-moment-action"><button class="moment-action-button moment-action-button__message"><i class="material-icons">mail_outline</i></button></div></div><div class="moment-content-info"><div class="moment-content-info__like-container"><div class="content-info__like-icon"><i class="material-icons">favorite</i></div><div class="content-info__like-text"></div></div></div><div class="cloud-moment-reply-container monent-content-container"><div class="moment-reply-box"><div class="moment-reply-box__avator-container"><img class="moment-reply-box__avator cloud-avator__img" src="../resource/avator/avator_1.png" width="32px" height="32px"                                alt="avator" /></div><div class="moment-reply-box__input-container"><textarea class="moment-reply-box__input" placeholder="回复动态内容"></textarea><div class="moment-reply-box__action-container"><div class="reply-box__action__submit-container"><button class="reply-box__action__submit">发表</button></div></div></div></div></div><ol class="moment-reply-stream"></ol></div></div></div></li>');
    /* 设置动态id绑定 */
    moment_template.attr("twitter-id", momentData.twitterId);
    moment_template.find(".cloud-moment").attr("twitter-id", momentData.twitterId);
    /* 绑定用户头像 */
    moment_template.find(".cloud-moment-avator__img").attr("src", momentData.userAvatorUrl);
    /* 绑定用户昵称 */
    moment_template.find(".moment-user-name").html(momentData.userName);
    /* 绑定用户id */
    moment_template.find(".moment-user-id").html(momentData.userNo);
    /* 绑定动态发送时间 */
    moment_template.find(".moment-time").html(momentData.twitterDate);
    /* 绑定动态内容 */
    moment_template.find(".cloud-moment-content__text").html(momentData.twitterContent);
    /* 绑定喜欢按钮动画 */
    animatetoggleLike(moment_template.find(".moment-action-button__favorite"), momentData.twitterId);
    /* 菜单初始化 */
    initMenu(moment_template.find(".moment-menu"), momentData.twitterId, momentData.userNo);
    /* 添加动态到页面 */
    //绑定twitterId
    moment_template.find(".cloud-moment-action-container").attr("twitter-id");
    //喜欢的数量
    moment_template.find(".action-badage__favorite-num").html(momentData.likeNum);
    //喜欢的详情
    moment_template.find(".content-info__like-text").html(momentData.likeUser);
    //转发的数量
    moment_template.find(".action-badage__transmit-num").html(momentData.transpondNum);
    //评论的数量
    moment_template.find(".action-badage__comment-num").html(momentData.replyNum);
    //回复头像
    moment_template.find(".moment-reply-box__avator").attr("src", user.avator + "?t=" + Math.random());

    //图片url
    var twitterImgUrl = momentData.twitterImgUrl;
    var eachImgUrl = twitterImgUrl.split(",");
    for (var i = 0; i < eachImgUrl.length - 1; i++) {
        //动态添加图片
        var imgItem = $(" <div class=\"moment-content-img-item\"></div>");
        var img = $("<img class=\"moment-content-img\" width=\"100%\" alt=\"Picture\" />");
        img.attr("src", eachImgUrl[i]);
        imgItem.append(img);
        moment_template.find(".cloud-moment-content__media").append(imgItem);
    }

    //评论详情
    var replys = momentData.replyInfos;//得到回复详情
    for (var i = 0; i < replys.length; i++) {
        //每一个盒子
        var reply_item = $('<li class="moment-reply-item"><div class="moment-reply"><div class="moment-reply-avator"><img class="moment-reply-avator__img" src="../resource/avator/avator_1.png" width="32px" height="32px" alt="avator"></div><div class="moment-reply-content"><div class="moment-reply-content__header moment-reply-content-container"><span class="moment-reply-user-name-container"><strong class="moment-reply-user-name"></strong></span><span class="moment-reply-content__split"></span><span class="moment-reply-user-id-container"><span>@</span><b class="moment-reply-user-id"></b></span><span class="moment-reply-content__split"></span><span class="moment-reply-time-container"><span class="moment-reply-time"></span></span></div><div class="moment-reply-content__text moment-reply-content-container"></div></div></div></li>');
        reply_item.find(".moment-reply-avator__img").attr("src", replys[i].userAvatorUrl);
        reply_item.find(".moment-reply-user-name").html(replys[i].userName);
        reply_item.find(".moment-reply-user-id").html(replys[i].userNo);
        reply_item.find(".moment-reply-time").html(replys[i].replyDate);
        reply_item.find(".moment-reply-content__text").html(replys[i].replyContent);
        moment_template.find(".moment-reply-stream").append(reply_item);
    }
    //绑定回复事件
    moment_template.find(".reply-box__action__submit").click(function () {
        addReply(momentData.twitterId);
    })


    if (momentData.isLike == "true") {//moment-action__favorite
        moment_template.find(".moment-action-button__favorite").addClass("moment-action-button__favorite-on");
        moment_template.find(".moment-action-button__favorite").empty();
        moment_template.find(".moment-action-button__favorite").append($("<i class='material-icons'>favorite</i>"));
    }


    if (direction == "totop") {
        $(".timeline-stream>.timeline-stream-item:first").before(moment_template);
    } else {
        moment_template.appendTo($(".timeline-stream"));
    }
    eventBind_MomentMenu(momentData.twitterId);
}

function initMenu(moment_menu, twitter_id, userId) {
    /* 动态菜单事件绑定 */
    var $munu_button = moment_menu.find(".moment-menu__button");
    var $munu_area = moment_menu.find(".moment-menu__button__area")
    $munu_button.attr({"id": twitter_id, "twitter-id": twitter_id});
    $munu_area.attr({"for": twitter_id, "twitter-id": twitter_id});
    if (user.userId != userId) {
        /*禁用本用户对其他动态的删除按钮*/
        $munu_area.find(".moment-menu__button-delete").attr("disabled", true);
    }
}

function eventBind_MomentMenu(twitter_id) {
    /* 激活菜单 */
    componentHandler.upgradeElement($(".moment-menu__button[twitter-id=" + twitter_id + "]")[0]);
    componentHandler.upgradeElement($(".moment-menu__button__area[twitter-id=" + twitter_id + "]")[0]);
    /* 绑定菜单按钮事件 */
    var munuButton = $(".moment-menu__button__area[twitter-id=" + twitter_id + "]");
    munuButton.find(".moment-menu__button-block").bind("click", function (event) {
        $(".timeline-stream-item[twitter-id=" + $(this).parent().attr("twitter-id") + "]").remove();
        alert("屏蔽该条动态成功");
    });
    munuButton.find(".moment-menu__button-report").bind("click", function (event) {
        alert("举报成功");
    });
    munuButton.find(".moment-menu__button-delete").bind("click", function (event) {
        $(".timeline-stream-item[twitter-id=" + $(this).parent().attr("twitter-id") + "]").remove();
        /* 绑定删除函数 */
        var delete_twitter_id = $(this).parent().attr("twitter-id");
        alert("删除该条动态成功\n删除动态ID:" + delete_twitter_id);
        /* 通过回传 delete_twitter_id 删除动态信息 */
    });
}

/* moment-box动态效果 */
function animatetoggleLike(bindItemThis, twitterId) {
    /* 点击喜欢函数 */
    /*  $(".moment-action-button__favorite") */
    bindItemThis.bind("click", function (event) {
        if (bindItemThis.hasClass("moment-action-button__favorite-on")) {//取消喜欢
            cancelLike(twitterId, bindItemThis);
        } else {//变成喜欢
            addLike(twitterId, bindItemThis);
        }
    });
}

function addLike(twtiiterId, bindItemThis) {
    $.ajax({
        url: "add.do",
        type: "POST",
        data: {
            type: "like",
            like: twtiiterId
        },
        success: function (data) {
            bindItemThis.addClass("moment-action-button__favorite-on");
            bindItemThis.empty();
            bindItemThis.append($("<i class='material-icons'>favorite</i>"));
            var str = data.split("||");
            $(".cloud-moment[twitter-id='" + twtiiterId + "'] .action-badage__favorite-num").html(str[0]);
            $(".cloud-moment[twitter-id='" + twtiiterId + "'] .content-info__like-text").html(str[1]);
        },
        error: function (data) {

        }
    })
}

function cancelLike(twtiiterId, bindItemThis) {
    $.ajax({
        url: "remove.do",
        type: "POST",
        data: {
            type: "like",
            like: twtiiterId
        },
        success: function (data) {
            bindItemThis.removeClass("moment-action-button__favorite-on");
            bindItemThis.empty();
            bindItemThis.append($("<i class='material-icons'>favorite_border</i>"));
            var str = data.split("||");
            $(".cloud-moment[twitter-id='" + twtiiterId + "'] .action-badage__favorite-num").html(str[0]);
            $(".cloud-moment[twitter-id='" + twtiiterId + "'] .content-info__like-text").html(str[1]);
        },
        error: function (data) {

        }
    })
}

function addReply(twitterId) {
    var replyContent = $(".cloud-moment[twitter-id=" + twitterId + "] .moment-reply-box__input").val();
    $.ajax({
        url: "add.do",
        type: "POST",
        data: {
            type: "reply",
            twitterId: twitterId,
            replyContent: replyContent
        },
        dataType: "JSON",
        success: function (data) {
            var replyContent = $(".cloud-moment[twitter-id=" + twitterId + "] .moment-reply-box__input").val("");
            var reply_itemNew = $('<li class="moment-reply-item"><div class="moment-reply"><div class="moment-reply-avator"><img class="moment-reply-avator__img" src="../resource/avator/avator_1.png" width="32px" height="32px" alt="avator"></div><div class="moment-reply-content"><div class="moment-reply-content__header moment-reply-content-container"><span class="moment-reply-user-name-container"><strong class="moment-reply-user-name"></strong></span><span class="moment-reply-content__split"></span><span class="moment-reply-user-id-container"><span>@</span><b class="moment-reply-user-id"></b></span><span class="moment-reply-content__split"></span><span class="moment-reply-time-container"><span class="moment-reply-time"></span></span></div><div class="moment-reply-content__text moment-reply-content-container"></div></div></div></li>');
            reply_itemNew.find(".moment-reply-avator__img").attr("src", data.userAvatorUrl);
            reply_itemNew.find(".moment-reply-user-name").html(data.userName);
            reply_itemNew.find(".moment-reply-user-id").html(data.userNo);
            reply_itemNew.find(".moment-reply-time").html(data.replyDate);
            reply_itemNew.find(".moment-reply-content__text").html(data.replyContent);
            $(".cloud-moment[twitter-id=" + twitterId + "] .moment-reply-stream").prepend(reply_itemNew);
        },
        error: function (data) {

        }
    })
}