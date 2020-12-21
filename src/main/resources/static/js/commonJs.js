(function () {
    let strFullPath = window.document.location.href
    let strPath = window.document.location.pathname
    let pos = strFullPath.indexOf(strPath)
    let prePath = strFullPath.substring(0, pos)
    let postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)
    let appRootPath = prePath + postPath

    let jsHeader = "<script type='text/javascript' src='" + appRootPath + "/"
    let jsFooter = "'></script>"
    document.write(jsHeader+ "js/vue.min.js" + jsFooter)
    document.write(jsHeader+ "js/jquery.min.js" + jsFooter)
    document.write(jsHeader+ "js/jquery.cookie.js" + jsFooter)
    document.write(jsHeader+ "js/layui/layui.js" + jsFooter)
    document.write('<script type="text/javascript">let token = $.cookie("token")</script>')
    document.write("<script type='text/javascript'>let ctx ='" + postPath + "'</script>")

    let cssHeader = "<link rel='stylesheet' type='text/css' href='" + appRootPath + "/"
    let cssFooter = "'></link>"
    document.write(cssHeader + "/js/layui/css/layui.css" + cssFooter)
})();

