var exec = require('cordova/exec');
var modex = {};

modex.coolMethod = function (success,error){
     console.log('cool...');
     cordova.exec(success, error, 'UnitInit', 'coolMethod', []);
};
module.exports = modex;