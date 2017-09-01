/**
 * 使用示例
 //分页div
 var pageDiv = $("div.pageList");
 //参数列表
 var options = {
        index:1,//当前页码
        total:15,//总页数
        numbers:6,//显示页码数
        //其他自定义参数
    };
 //创建分页对象
 Page.init(pageDiv,options, function(data){
        console.info(data);
    });
author: lixk
date: 2016/11/24
 */

var Page = {
  pageDiv: null,
  options: null,

  /**
   * 初始化分页
   * @param pageDiv 分页div
   * @param options 参数{index:...(当前页码), total:...(总页数), numbers:...(最多显示页码数), ...}
   * @param callback 回调函数名
   */
  init: function(pageDiv, options, callback) {
    this.pageDiv = $(pageDiv);
    this.options = options || {};
    //如果没有传入index，设置默认值为1
    if (!this.options.index) {
      this.options.index = 1;
    }
    //渲染分页
    this.render();

    //代理绑定事件
    this.pageDiv.off("click", "button");
    this.pageDiv.on("click", "button", function() {
      var value = $(this).text();
      var index = Page.options.index;
      if ("上一页" == value && Page.options.index > 1) {
        index = Number(Page.options.index) - 1;
      } else if ("下一页" == value && Page.options.index < Page.options.total) {
        index = Number(Page.options.index) + 1;
      } else if ("GO" == value) {
        var go = Page.pageDiv.find("input.size");
        var n = go.val();
        if (!isNaN(n) && n >= 1 && n <= Page.options.total) {
          index = Math.floor(n); //防止输入小数
        }
        go.val("");
      } else if (!isNaN(value)) { //点击页码
        index = value;
      }
      //当页码发生变化
      if (Page.options.index != index) {
        Page.options.index = index;
        options["index"] = Number(Page.options.index);
        //调用回调函数
        callback(options);
        //重新渲染分页
        Page.render();
      }
    });
  },
  /**
   * 渲染分页
   */
  render: function() {

    /*-----------------DOM渲染--------------------*/
    var s = '<button type="submit" class="prevPage">上一页</button>';

    if (this.options.total <= this.options.numbers) { //总页数小于等于可显示页码数，全部显示
      for (var i = 1; i <= this.options.total; i++) {
        s += '<button type="submit">' + i + '</button>';
      }
    } else { //总页数大于可显示页码数
      if (this.options.index <= this.options.numbers - 3) { //所选页码在可显示页码数的...前一位之前
        for (var i = 1; i <= this.options.numbers - 2; i++) {
          s += '<button type="submit">' + i + '</button>';
        }
        s += '<button type="submit" class="pageMore">...</button>';
        s += '<button type="submit">' + this.options.total + '</button>';
      } else if (this.options.index >= this.options.total - (this.options.numbers -
          4)) {
        s += '<button type="submit">1</button>';
        s += '<button type="submit" class="pageMore">...</button>';
        for (var i = this.options.total - (this.options.numbers - 3); i <=
          this.options.total; i++) {
          s += '<button type="submit">' + i + '</button>';
        }
      } else {
        s += '<button type="submit">1</button>';
        s += '<button type="submit" class="pageMore">...</button>';
        var offset = Math.floor((this.options.numbers - 4) / 2);
        for (var i = this.options.index - offset; i <= Number(this.options.index) +
          offset; i++) {
          s += '<button type="submit">' + i + '</button>';
        }
        s += '<button type="submit" class="pageMore">...</button>';
        s += '<button type="submit">' + this.options.total + '</button>';
      }
    }

    s +=
      '<span class="sizePar">第<input type="text" class="size">页</span><button type="submit" class="Go">GO</button>';
    s += '<button type="submit" class="nextPage">下一页</button>';
    //将元素添加到页面
    this.pageDiv.html(s);

    /*-----------------样式渲染--------------------*/
    //选中页码
    Page.pageDiv.find("button").each(function() {
      if ($(this).text() == Page.options.index) {
        $(this).addClass("active");
      }
    });
    //上一页
    if (this.options.index > 1) {
      Page.pageDiv.find("button.prevPage").removeClass("disabled");
    } else {
      Page.pageDiv.find("button.prevPage").addClass("disabled");
    }
    //下一页
    if (this.options.index < this.options.total) {
      Page.pageDiv.find("button.nextPage").removeClass("disabled");
    } else {
      Page.pageDiv.find("button.nextPage").addClass("disabled");
    }
  }
}
