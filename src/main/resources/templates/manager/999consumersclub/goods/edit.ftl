<#include "../../../common/meta.ftl"/>
<link rel="stylesheet" type="text/css" href="${base}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${base}/lib/webuploader/0.1.5/webuploader.css" />
<title>编辑商品</title>
<meta text="keywords" content="">
<meta text="description" content="">
<style>
    .uploader-list-container .placeholder {
        min-height: 102px;
        padding-top: 80px;
        background-position-y:20px;
    }
</style>
</head>
<body>
<article class="page-container" id="app">
    <form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name" v-model="good.name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品品牌：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="brand" name="brand">
            </div>
        </div>
        <div id="treeBox" class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品分类：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" readonly value="" @focus="showMenu()" placeholder="" id="categoryName" name="categoryName">
                <input type="hidden" class="input-text" placeholder="" id="categoryIds" name="categoryIds" v-model="good.categoryIds">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">商品图片：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="hidden" class="input-text" placeholder="" id="imgUrl" name="imgUrl" v-model="good.imgUrl">
                <div id="uploader" class="uploader-list-container" style="width: 85%">
                    <div class="queueList">
                        <div id="dndArea" class="placeholder">
                            <div id="filePicker1"></div>
                            <p>或将照片拖到这里</p>
                        </div>
                    </div>
                    <div class="statusBar" style="display:none;">
                        <div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
                        <div class="info"></div>
                        <div class="btns">
                            <div id="filePicker2"></div>
                            <div class="uploadBtn">开始上传</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>市场价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="marketPrice" name="marketPrice" v-model="good.marketPrice">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>门店价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="shopPrice" name="shopPrice" v-model="good.shopPrice">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>预警库存：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="warnStock" name="warnStock" v-model="good.warnStock">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>总库存：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="goodsStock" name="goodsStock" v-model="good.goodsStock">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="goodsUnit" name="goodsUnit" v-model="good.goodsUnit">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上下架：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="saleStatus" v-model="good.saleStatus" value="0" type="radio" id="type-1">
                    <label for="type-1">上架</label>
                </div>
                <div class="radio-box">
                    <input name="saleStatus" v-model="good.saleStatus" value="1" type="radio" id="type-2">
                    <label for="type-2">下架</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="description" name="description" v-model="good.description">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button class="btn btn-primary radius" type="button" @click="submit">提交</button>
            </div>
        </div>
    </form>
</article>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 99;">
    <ul id="tree" class="ztree" style="margin-top:0; width:160px;height: 100px;"></ul>
</div>
<#include "../../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${base}/lib/webuploader/0.1.5/webuploader.js"></script>
<script type="text/javascript">
    /**
     * 下拉树
     */
    var setting = {
        check: {
            enable: false,
            chkStyle: "radio",
            radioType: "all"
        },
        view: {
            dblClickExpand: false,
            showIcon: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
                nodes = zTree.getSelectedNodes(),
                v = "";
        // var dataObj = $("#parentName");
        // dataObj.attr("value", nodes[0].name);
        app.good.parentName = nodes[0].name;
        app.good.parentId = nodes[0].id;
    }

    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

//----------------------------------------------------------------------------------------

    var app = new Vue({
	el: '#app',
    data: {
        treeData: [],
        good: {
        	id: '',
            name: '',
            brand: '',
            categoryIds: '',
            imgUrl: '',
            marketPrice: '',
            shopPrice: '',
            warnStock: '',
            goodsStock: 0,
            goodsUnit: '',
            saleStatus: 0,
            description:''
        }
    },
	mounted: function() {
        this.getCategoryAll();
		this.good.id = getQueryString("id");
		this.getGoods();
	},
	methods: {
        getCategoryAll: function () {
            this.$http.get("${base}/manager/cc/rest/category/tree?goods=add").then(function(response) {
                var data = response.data;
                this.treeData = data.data;
                if ("0000" == data.code) {
                    $.fn.zTree.init($("#tree"), setting, data.data);
                    treeObj = $.fn.zTree.getZTreeObj("tree");
                } else {
                    $.Huimodalalert(data.msg,2000);
                }

            });
        },
        getGoods: function() {
            this.$http.get("${base}/manager/cc/rest/goods/get?id="+getQueryString("id")).then(function(response) {
                var data = response.data;
                if ("0000" == data.code) {
                	this.good.id = data.data.id;
                	this.good.name = data.data.name;
                	this.good.parentId = data.data.parentId;
                	this.good.status = data.data.status;
                	this.good.sort = data.data.sort;
                } else {
                    layer.msg(data.msg, {icon: 5, time: 1000});
				}

            });
		},
		submit: function() {
			if (!$("#form-member-add").valid())
				return;
			this.$http.post("${base}/manager/cc/rest/good/edit", this.good, {emulateJSON:true}).then(function(response) {
				var data = response.data;

                if ("0000" == data.code) {
                    layer.msg(data.msg, {icon: 6, time: 1000});
                    setTimeout("parentReload()", 500);
				} else {
                    layer.msg(data.msg, {icon: 5, time: 1000});
                }

            });
		},
        showMenu: function() {
            var dataObj = $("#parentName");
            var dataOffset = $("#parentName").offset();
            $("#menuContent").css({left:dataOffset.left + "px", top:dataOffset.top + dataObj.outerHeight() + "px"}).slideDown("fast");
            var heigth = this.getTreeDataLength(this.treeData);
            $(".ztree").height(heigth*20);
            $("body").bind("mousedown", onBodyDown);
        },
        getTreeDataLength: function(data) {
            var length = 0;
            for (var i = 0; i < data.length; i++) {
                if (data[i].children.length > 0) {
                    length +=this.getTreeDataLength(data[i].children);
                }
                length++;
            }
            return length;
        }
	}

});

$(function(){
	$("#form-member-add").validate({
        debug: true,
		rules:{
			loginName:{
				required:true,
                isRightfulString:true
			},
            password:{
				required:true,
			}
		},
        messages:{
            loginName:{
                isRightfulString:"只能输入英文字母大小写、数字、下划线"
            }
        },
		onkeyup:false,
		success:"valid",
        unhighlight: function (element, errorClass, validClass) { //验证通过
            $(element).tooltip('destroy').removeClass(errorClass);
        },
        errorPlacement: function (error, element) {
            if ($(element).next("div").hasClass("tooltip")) {
                $(element).attr("data-original-title", $(error).text()).tooltip("show");
            } else {
                $(element).attr("title",
                        $(error).text()).tooltip("show");
            }
        }
	});
});
    $(function() {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
    });
    // 图片上传
    jQuery(function() {
        var $ = jQuery,

                $wrap = $('#uploader'),

                // 图片容器
                $queue = $('<ul class="filelist"></ul>')
                        .appendTo( $wrap.find('.queueList') ),

                // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),

                // 文件总体选择信息。
                $info = $statusBar.find('.info'),

                // 上传按钮
                $upload = $wrap.find('.uploadBtn'),

                // 没选择文件之前的内容。
                $placeHolder = $wrap.find('.placeholder'),

                // 总体进度条
                $progress = $statusBar.find('.progress').hide(),

                // 添加的文件数量
                fileCount = 0,

                // 添加的文件总大小
                fileSize = 0,

                // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,

                // 缩略图大小
                thumbnailWidth = 110 * ratio,
                thumbnailHeight = 110 * ratio,

                // 可能有pedding, ready, uploading, confirm, done.
                state = 'pedding',

                // 所有文件的进度信息，key为file id
                percentages = {},
                // 判断浏览器是否支持图片的base64
                isSupportBase64 = ( function() {
                    var data = new Image();
                    var support = true;
                    data.onload = data.onerror = function() {
                        if( this.width != 1 || this.height != 1 ) {
                            support = false;
                        }
                    }
                    data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                    return support;
                } )(),

                // 检测是否已经安装flash，检测flash的版本
                flashVersion = ( function() {
                    var version;

                    try {
                        version = navigator.plugins[ 'Shockwave Flash' ];
                        version = version.description;
                    } catch ( ex ) {
                        try {
                            version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                    .GetVariable('$version');
                        } catch ( ex2 ) {
                            version = '0.0';
                        }
                    }
                    version = version.match( /\d+/g );
                    return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
                } )(),

                supportTransition = (function(){
                    var s = document.createElement('p').style,
                            r = 'transition' in s ||
                                    'WebkitTransition' in s ||
                                    'MozTransition' in s ||
                                    'msTransition' in s ||
                                    'OTransition' in s;
                    s = null;
                    return r;
                })(),

                // WebUploader实例
                uploader;

        if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {
            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function(container) {
                    window['expressinstallcallback'] = function( state ) {
                        switch(state) {
                            case 'Download.Cancelled':
                                alert('您取消了更新！')
                                break;

                            case 'Download.Failed':
                                alert('安装失败')
                                break;

                            default:
                                alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = 'expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' +  swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">'  +
                            '<param name="movie" value="' + swf + '" />' +
                            '<param name="wmode" value="transparent" />' +
                            '<param name="allowscriptaccess" value="always" />' +
                            '</object>';

                    container.html(html);

                })($wrap);

                // 压根就没有安转。
            } else {
                $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            alert( 'Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker1',
                label: '点击选择图片'
            },
            dnd: '#dndArea',
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },
            paste: '#uploader',
            swf: 'lib/webuploader/0.1.5/Uploader.swf',
            disableGlobalDnd: true,
            chunked: false,
            server: '${base}/manager/cc/rest/image/upload',
            fileNumLimit: 1,
            fileSizeLimit: 10 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 10 * 1024 * 1024    // 50 M
        });
        //返回结果处理
        uploader.on('uploadSuccess', function(file,response) {
            // alert(JSON.stringify(response));
            console.log("file:",file);
            console.log("response:",response);
            $("#imgUrl").val(response.data)
        });
        // 添加“添加文件”的按钮，
        // uploader.addButton({
        //     id: '#filePicker2',
        //     label: '继续添加'
        // });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                    $btns = $('<div class="file-panel">' +
                            '<span class="cancel">删除</span>' +
                            '<span class="rotateRight">向右旋转</span>' +
                            '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                    $prgress = $li.find('p.progress span'),
                    $wrap = $li.find( 'p.imgWrap' ),
                    $info = $('<p class="error"></p>'),

                    showError = function( code ) {
                        switch( code ) {
                            case 'exceed_size':
                                text = '文件大小超出';
                                break;

                            case 'interrupt':
                                text = '上传暂停';
                                break;

                            default:
                                text = '上传失败，请重试';
                                break;
                        }

                        $info.text( text ).appendTo( $li );
                    };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    var img = $('<img src="'+src+'">');
                    $wrap.empty().append( img );
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                        deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                }
            });
            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                    total = 0,
                    spans = $progress.children(),
                    percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;

            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                            stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }
            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.parent().removeClass('filled');
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.parent().addClass('filled');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $upload.text( '开始上传' ).addClass( 'disabled' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        alert( '上传成功' );
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                    $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });

        uploader.onError = function( code ) {
            switch( code ) {
                case 'Q_EXCEED_NUM_LIMIT':
                    alert( 'Error: 最多上传一张图片');
                    break;

                case 'Q_EXCEED_SIZE_LIMIT':
                    alert( 'Error: 文件大小限制10M');
                    break;

                case 'Q_TYPE_DENIED':
                    alert( 'Error: 文件类型不正确');
                    break;
            }
        };

        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();
    });

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>