var pageIdField = "data-pageId";

function getPageId(element) {
    if (element instanceof jQuery) {
        return element.attr(pageIdField);
    } else {
        return $(element).attr(pageIdField);
    }
}

function findTabTitle(pageId) {
    var $ele = null;
    $(".page-tabs-content").find("a.menu_tab").each(function () {
        var $a = $(this);
        if ($a.attr(pageIdField) == pageId) {
            $ele = $a;
            return false;
        }
    });
    return $ele;
}

function findTabPanel(pageId) {
    var $ele = null;
    $("#tab-content").find("div.tab-pane").each(function () {
        var $div = $(this);
        if ($div.attr(pageIdField) == pageId) {
            $ele = $div;
            return false;
        }
    });
    return $ele;
}

function findIframeById(pageId) {
    return findTabPanel(pageId).children("iframe");
}

function getActivePageId() {
    var $a = $('.page-tabs-content').find('.active');
    return getPageId($a);
}

function canRemoveTab(pageId) {
    return findTabTitle(pageId).find('.fa-remove').size() > 0;
}


var addTabs = function (options) {
    var defaultTabOptions = {
        id: Math.random() * 200,
//        urlType: "relative",
        title: "새탭"
    };

    options = $.extend(true, defaultTabOptions, options);

    if (options.urlType === "relative") {
        var basePath = window.location.pathname + "/../";
        options.url = basePath + options.url;
    }

    var pageId = options.id;

    if (findTabPanel(pageId) === null) {

        var $title = $('<a href="javascript:void(0);"></a>').attr(pageIdField, pageId).addClass("menu_tab");

        var $text = $("<span class='page_tab_title'></span>").text(options.title).appendTo($title);

        if (options.close) {
            var $i = $("<i class='fa fa-remove page_tab_close' style='cursor: pointer' onclick='closeTab(this);'></i>").attr(pageIdField, pageId).appendTo($title);

        }

        $(".page-tabs-content").append($title);
        var $tabPanel = $('<div role="tabpanel" class="tab-pane"></div>').attr(pageIdField, pageId);

        if (options.content) {
            $tabPanel.append(options.content);
        } else {

            App.blockUI({
                target: '#tab-content',
                boxed: true,
                message: '로딩중......'//,
                // animate: true
            });

            var $iframe = $("<iframe></iframe>").attr("src", options.url).css("width", "100%").attr("frameborder", "no").attr("id", "iframe_" + pageId).addClass("tab_iframe").attr(pageIdField, pageId);
            
            $iframe.on("load",function(){
                App.unblockUI('#tab-content');
                App.fixIframeCotent();
            });

            $tabPanel.append($iframe);

        }

        $("#tab-content").append($tabPanel);

    }

    activeTabByPageId(pageId);

};


var closeTab = function (item) {
    var pageId = getPageId(item);
    closeTabByPageId(pageId);
};

function closeTabByPageId(pageId) {
    var $title = findTabTitle(pageId);
    var $tabPanel = findTabPanel(pageId);

    if ($title.hasClass("active")) {
        var $nextTitle = $title.next();
        var activePageId;
        if ($nextTitle.length > 0) {
            activePageId = getPageId($nextTitle);
        } else {
            activePageId = getPageId($title.prev());
        }
        if(activePageId ){
            setTimeout(function () {
                activeTabByPageId(activePageId);
            }, 100);
        }
        

    } else {
    }

    $title.remove();
    $tabPanel.remove();


}

function closeTabOnly(pageId) {
    var $title = findTabTitle(pageId);
    var $tabPanel = findTabPanel(pageId);
    $title.remove();
    $tabPanel.remove();
}

var closeCurrentTab = function () {
    var pageId = getActivePageId();
    if (canRemoveTab(pageId)) {
        closeTabByPageId(pageId);
    }
};

function refreshTabById(pageId) {
    var $iframe = findIframeById(pageId);
    var url = $iframe.attr('src');

    if (url.indexOf(top.document.domain) < 0) {
        $iframe.attr("src", url);
    } else {
        $iframe[0].contentWindow.location.reload(true);
    }

    App.blockUI({
        target: '#tab-content',
        boxed: true,
        message: '갱신중......'
    });
}

var refreshTab = function () {
    var pageId = getActivePageId();
    refreshTabById(pageId);
};

function getTabUrlById(pageId) {
    var $iframe = findIframeById(pageId);
    return $iframe[0].contentWindow.location.href;
}

function getTabUrl(element) {
    var pageId = getPageId(element);
    getTabUrlById(pageId);
}


function editTabTitle(pageId, title) {
    var $title = findTabTitle(pageId);
    var $span = $title.children("span.page_tab_title");
    $span.text(title);
}

var calSumWidth = function (element) {
    var width = 0;
    $(element).each(function () {
        width += $(this).outerWidth(true);
    });
    return width;
};
var scrollToTab = function (element) {
    var marginLeftVal = calSumWidth($(element).prevAll()),
        marginRightVal = calSumWidth($(element).nextAll());
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    var scrollVal = 0;
    if ($(".page-tabs-content").outerWidth() < visibleWidth) {
        scrollVal = 0;
    } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
        if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
            scrollVal = marginLeftVal;
            var tabElement = element;
            while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                scrollVal -= $(tabElement).prev().outerWidth();
                tabElement = $(tabElement).prev();
            }
        }
    } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
        scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
    }
    $('.page-tabs-content').animate({
        marginLeft: 0 - scrollVal + 'px'
    }, "fast");
};
var scrollTabLeft = function () {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    var scrollVal = 0;
    if ($(".page-tabs-content").width() < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".menu_tab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).prev();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
        }
    }
    $('.page-tabs-content').animate({
        marginLeft: 0 - scrollVal + 'px'
    }, "fast");
};
var scrollTabRight = function () {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    var scrollVal = 0;
    if ($(".page-tabs-content").width() < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".menu_tab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        scrollVal = calSumWidth($(tabElement).prevAll());
        if (scrollVal > 0) {
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        }
    }
};

var closeOtherTabs = function (isAll) {
    if (isAll) {
        $('.page-tabs-content').children("[" + pageIdField + "]").find('.fa-remove').parents('a').each(function () {
            var $a = $(this);
            var pageId = getPageId($a);
            closeTabOnly(pageId);

        });
        var firstChild = $(".page-tabs-content").children().eq(0); 
        if (firstChild) {
            activeTabByPageId(getPageId(firstChild));
        }
    } else {
        $('.page-tabs-content').children("[" + pageIdField + "]").find('.fa-remove').parents('a').not(".active").each(function () {
            var $a = $(this);
            var pageId = getPageId($a);
            closeTabOnly(pageId);

        });

    }
};

function activeTabByPageId(pageId) {
    $(".menu_tab").removeClass("active");
    $("#tab-content").find(".active").removeClass("active");
    var $title = findTabTitle(pageId).addClass('active');
    findTabPanel(pageId).addClass("active");
    scrollToTab($title[0]);
}

$(function () {
    var $tabs = $(".menuTabs");
    $tabs.on("click", ".menu_tab", function () {
        var pageId = getPageId(this);
        activeTabByPageId(pageId);
    });

//    $tabs.on("dblclick", ".menu_tab", function () {
//        var pageId = getPageId(this);
//        refreshTabById(pageId);
//    });

//    function findTabElement(target) {
//        var $ele = $(target);
//        if (!$ele.is("a")) {
//            $ele = $ele.parents("a.menu_tab");
//        }
//        return $ele;
//    }

//    context.init({
//        preventDoubleContext: false,
//        compress: true
//    });
//    context.attach('.page-tabs-content', [
//
//        {
//            text: '새로고침',
//            action: function (e, $selector, rightClickEvent) {
//                var pageId = getPageId(findTabElement(rightClickEvent.target));
//                refreshTabById(pageId);
//
//            }
//        },
//        {
//            text: "새창에서보기",
//            action: function (e, $selector, rightClickEvent) {
//
//                var pageId = getPageId(findTabElement(rightClickEvent.target));
//                var url = getTabUrlById(pageId);
//                window.open(url);
//
//            }
//        }
//    ]);

});