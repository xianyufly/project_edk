/**
 * 重写Array的移除方法
 * @param {} dx
 * @return {Boolean}
 */
Array.prototype.remove = function(dx) {
	if(isNaN(dx) || dx > this.length) {
		return false;
	}
		
	for(var i=0, n=0; i<this.length; i++) {
		if(this[i] != this[dx]) {
			this[n++] = this[i];
		}
	}
	
	this.length -= 1;
}

/**
 * 重写Array的indexOf方法
 */
if(!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(val) {
	   var value = this;
	   for(var i =0; i < value.length; i++) {
	      if(value[i] == val) return i;
	   }
	   return -1;
	};  
}

$.extend($.fn.validatebox.defaults.rules, {
    minLength: {
        validator: function(value, param) {
            return value.length >= param[0];
        },  
        message: 'Please enter at least {0} characters.'
    },
    
    ip: {
    	validator: function(value, param) {    		
            var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;

			return value.match(reg);
        },  
        message: '请输入正确的IP地址'
    },
    
    port: {
    	validator: function(value, param) {    		
            if(value > 0 && value <= 65535) {
            	return true;
            }
            else {
            	return false;
            }
        },  
        message: '请输入正确的端口'
    }
});