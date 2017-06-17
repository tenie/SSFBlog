/**
 * Load i18n properties
 */
(function($){
    $.dmsI18n = function(){};

    $.extend($.dmsI18n,
        {
            languageMap : {"zh_CN":"zh","en_US":"en"}
        },
        {
            loadProperties : function() {
                jQuery.i18n.properties({
                 //   language: this.languageMap[$.cookie("language")],
                	 language: this.languageMap["zh"],
                    name: "i18n",
                    //文件名
                    path: "./assets/global/properties/",
                    mode: "map"
                });
            }
        },
        {
            replaceI18n : function (htmlCode) {
            	//替换规则
                return htmlCode.replace(/#[tenie\.i18n\.[\w|\d]*]#/ig, function (i18n_str) {
                    return jQuery.i18n.prop(i18n_str.substring(2, i18n_str.length - 2));
                });
            }
        }
    );
})(jQuery);

$(function(){
    $.dmsI18n.loadProperties();
});