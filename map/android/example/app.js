var MAP = require('com.vbg.map');
var getType1Pins;
var annotation_pin_data = [];
var MAP_VIEW = MAP.createView({
	top : 0,
	left : 0,
	right : 0,
	bottom : 0,
	mapType : MAP.NORMAL_TYPE,
	animate : true,
	regionFit : true,
	userLocation : true,
	bubbleParent : false
});
$.map_container.add(MAP_VIEW);
var latlongData = [{
	lat : 28.635308,
	longi : 77.224960,
	title : 'Delhi',
	subTitle : 'Delhi',
	header : "Delhi",
	timing : "10AM-7PM"
}, {
	lat : 19.075984,
	longi : 72.877656,
	title : 'Mumbai',
	subTitle : 'Maharashtra',
	header : "Mumbai",
	timing : "9AM-6PM"
}, {
	lat : 18.520430,
	longi : 73.856744,
	title : 'Pune',
	subTitle : 'Maharashtra',
	header : "Pune",
	timing : "11AM-8PM"
}, {
	lat : 12.971599,
	longi : 77.594563,
	title : 'Bengaluru',
	subTitle : 'Karnataka',
	header : "Bengaluru",
	timing : "8AM-8PM"
}];

getType1Pins = function() {
	annotation_pin_data=[];
	for (var i = 0; i < latlongData.length; i++) {
		var annotation = MAP.createAnnotation({
			latitude : latlongData[i].lat,
			longitude : latlongData[i].longi,
			title : latlongData[i].title,
			subtitle : latlongData[i].subTitle,
			pincolor : MAP.ANNOTATION_ORANGE,
			data : {
				isFirstType : true,
				header : latlongData[i].header,
				name : "T1 :" + i,
				timing : latlongData[i].timing,
				isClosed : 'Sample line',
				rating : i + 1,
				ratingHeader:"Star:",
				defaultRatingImage : "/images/star_deselected.png",
				selectedRatingImage : "/images/star_selected.png"
				
			},
			canShowCallout : true,
			draggable : true
		});
		annotation_pin_data.push(annotation);
	};

	MAP_VIEW.removeAllAnnotations();
	MAP_VIEW.annotations = annotation_pin_data;
};

function getType2Pins() {
annotation_pin_data=[];
	for (var i = 0; i < latlongData.length; i++) {
		var annotation = MAP.createAnnotation({
			latitude : latlongData[i].lat,
			longitude : latlongData[i].longi,
			title : latlongData[i].title,
			subtitle : latlongData[i].subTitle,
			pincolor : MAP.ANNOTATION_ORANGE,
			data : {
				isFirstType : false,
				header : latlongData[i].header,
				name : "T2 :" + i,
				timing : latlongData[i].timing,
				isClosed : 'Sample Line',
				rating : i + 1,
				ratingHeader:"Rate:",
				defaultRatingImage : "/images/star_deselected.png",//We are not using this
				selectedRatingImage : "/images/star_selected.png"//We are not using this

			},
			canShowCallout : true,
			draggable : true
		});
		annotation_pin_data.push(annotation);
	};
	MAP_VIEW.removeAllAnnotations();
	MAP_VIEW.annotations = annotation_pin_data;
};

function doAddType1(e) {
	getType1Pins();
}

function doClearMap(e) {
	MAP_VIEW.removeAllAnnotations();
}

function doAddType2(e) {
	getType2Pins();
}

$.win.open();
