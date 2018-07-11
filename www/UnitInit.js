var exec = require('cordova/exec');
module.exports = {
	__setUpAd: function(gameId) {
    	    console.log("ID :: "+gameId);
            cordova.exec(null,null,'UnitInit','__setUpAd',[gameId]);
    },
    __showAds: function(){
        cordova.exec(null,null,'UnitInit','__showAds');
    },
};
