/**
 * Created by wuyuquan on 2017-08-21.
 */
function js_method(id) {
    $.ajax({
        type: "POST",
        url:"http://127.0.0.1:8080/test?name="+id,
        cache:false,
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if(data.success){
                alert(data);
                //todo
                window.location.href="http://baidu.com";
//                    window.navigator("http://baidu.com");
            }
            else{
                //todo
                alert("error");
            }
        }
    });
}