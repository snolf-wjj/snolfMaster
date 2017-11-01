/**
 * Created by Administrator on 2017/10/12.
 */

var flashUrl1 = "http://cdn.abowman.com/widgets/hamster/hamster.swf?";
var flashUrl2 = "http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_wh.swf?";
var flashUrl3 = "http://cdn.abowman.com/widgets/newtonscradle/newtonsCradle.swf?";
var params3 = "up_barColor=333333&up_ballColor=3C3C3C&up_bgColor=FFFFFF&up_speakerColor=999999&up_stringColor=666666&up_numBalls=7&";
var flashUrl4 = "http://cdn.abowman.com/widgets/discdrop/discDrop.swf?";
var flashUrl5 = "http://cdn.abowman.com/widgets/pendulumclock/pendulumClockV2.swf?";
var flashUrl6 = "http://cdn.abowman.com/widgets/fish/fish.swf?";
var params6 = "up_fishColor5=FA1D00&up_fishColor9=728A13&up_fishColor10=2B227D&up_numFish=10&up_fishColor7=163EF0&up_fishColor4=450309&up_foodColor=F59302&up_backgroundColor=FFFFFF&up_fishColor8=B01747&up_fishColor3=EBE315&up_fishName=Fish&up_fishColor1=F45540&up_fishColor2=241715&up_backgroundImage=http://&up_fishColor6=22F522"

var a = "";
var b = "";
function c() {
	var d = a + b;
	var e = "";
	e += '<object type="application/x-shockwave-flash" style="outline:none;" data="' + d + '" width="300" height="200">';
	e += '<param name="movie" value="' + d + '"/>';
	e += '<param name="AllowScriptAccess" value="always">';
	e += '<param name="wmode" value="opaque">';
	e += '<param name="bgcolor" value=""/>';
	e += '</object>';
	document.write(e)
}