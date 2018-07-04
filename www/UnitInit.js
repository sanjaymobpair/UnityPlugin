var exec = require('cordova/exec');
module.exports = {
	hello: function() {
        cordova.exec(null,null,'UnitInit','hello',[]);
    },
};