AJS.$(function() {

    if (AJS.$("#login-form").length) {
	AJS.$("#login-form").hide();
	AJS.$('<div class="field-group"><a class="aui-button aui-style aui-button-primary" href="plugins/servlet/saml/auth" style="align:center;">Use Corporate login</a></div><h2 style="margin-top:10px"></h2>').insertBefore(AJS.$("#login-form .form-body"));

        var query = location.search.substr(1);
        query.split("&").forEach(function(part) {
            var item = part.split("=");
            if (item.length == 2 && item[0] == "samlerror") {
                var errorKeys = {};
                errorKeys["general"] = "General SAML configuration error";
                errorKeys["user_not_found"] = "User was not found";
                errorKeys["plugin_exception"] = "SAML plugin internal error";
                AJS.$("#login-form").show();
		var message = '<div class="aui-message closeable error">' + errorKeys[item[1]] + '</div>';
                AJS.$(message).insertBefore(AJS.$("#login-form"));
            }
        });

        if (location.search=='?logout=true') {
		$.ajax({
                    url: AJS.contextPath() + "/plugins/servlet/saml/getajaxconfig?param=logoutUrl",
                    type: "GET",
                    error:function () {                       
                            },
                    success: function (response) {
                        if (response != "") {
			    AJS.$('<p>Please wait while we redirect you to your company log out page</p>').insertBefore(AJS.$("#login-form"));
			    window.location.href = response;
			    return;
                        }
                    }
               });
               return;
	}

               AJS.$.ajax({
                    url: AJS.contextPath() + "/plugins/servlet/saml/getajaxconfig?param=idpRequired",
                    type: "GET",
                    error:function () {                       
                            },
                    success: function (response) {
                        if (response=="true") {
                        	// AJS.$('<img src="download/resources/com.bitium.confluence.SAML2Plugin/images/progress.png"/>').insertBefore(AJS.$(".aui.login-form-container"));
            				AJS.$('<p>Please wait while we redirect you to your company log in page</p>').insertBefore(AJS.$("#login-form"));
            				window.location.href = 'plugins/servlet/saml/auth';
			        
			} else {
				AJS.$("#login-form").show();
			}
                    }
               });

     }

});
