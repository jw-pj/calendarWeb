var month = [
	"Januar",
	"Februar",
	"März",
	"April",
	"Mai",
	"Juni",
	"Juli",
	"August",
	"September",
	"Oktober",
	"November",
	"Dezember"
];
var weekday = [
	"Sonntag",
	"Montag",
	"Dienstag",
	"Mittwoch",
	"Donnerstag",
	"Freitag",
	"Samstag"
];
var weekdayShort = [
	"so",
	"mo",
	"di",
	"mi",
	"do",
	"fr",
	"sa"
];
var monthDirection = 0;

function getNextMonth() {
	monthDirection++;
	var current;
	var now = new Date();
	if (current == 11) {
		current = new Date(now.getFullYear() + monthDirection, 0, 1);
	} else {
		current = new Date(now.getFullYear(), now.getMonth() + monthDirection, 1);
	}
	initCalender(getMonth(current));
}

function getPrevMonth() {
	monthDirection--;
	var current;
	var now = new Date();
	if (current == 11) {
		current = new Date(current + monthDirection, 0, 1);
	} else {
		current = new Date(now.getFullYear(), now.getMonth() + monthDirection, 1);
	}
	initCalender(getMonth(current));
}
Date.prototype.isSameDateAs = function (pDate) {
	return (
		this.getFullYear() === pDate.getFullYear() &&
		this.getMonth() === pDate.getMonth() &&
		this.getDate() === pDate.getDate()
	);
};

function getMonth(currentDay) {
	var now = new Date();
	var currentMonth = month[currentDay.getMonth()];
	var monthArr = [];
	for (i = 1 - currentDay.getDate(); i < 31; i++) {
		var tomorrow = new Date(currentDay);
		tomorrow.setDate(currentDay.getDate() + i);
		if (currentMonth !== month[tomorrow.getMonth()]) {
			break;
		} else {
			monthArr.push({
				date: {
					weekday: weekday[tomorrow.getDay()],
					weekday_short: weekdayShort[tomorrow.getDay()],
					day: tomorrow.getDate(),
					month: month[tomorrow.getMonth()],
					year: tomorrow.getFullYear(),
					current_day: now.isSameDateAs(tomorrow) ? true : false,
					date_info: tomorrow
				}
			});
		}
	}
	return monthArr;
}

function clearCalender() {
	$("table tbody tr").each(function () {
		$(this).find("td").removeClass("active selectable currentDay between hover").html("");
	});
	$("td").each(function () {
		$(this).unbind('mouseenter').unbind('mouseleave');
	});
	$("td").each(function () {
		$(this).unbind('click');
	});
	clickCounter = 0;
}

function initCalender(monthData) {
	var row = 0;
	var classToAdd = "";
	var currentDay = "";
	var today = new Date();

	clearCalender();
	$.each(monthData,
		function (i, value) {
			var weekday = value.date.weekday_short;
			var day = value.date.day;
			var column = 0;
			var index = i + 1;

			$(".sideb .header .month").html(value.date.month);
			$(".sideb .header .year").html(value.date.year);
			if (value.date.current_day) {
				currentDay = "currentDay";
        classToAdd = "selectable";
				$(".right-wrapper .header span").html(value.date.weekday);
				$(".right-wrapper .day").html(value.date.day);
				$(".right-wrapper .month").html(value.date.month);
			}
			if (today.getTime() < value.date.date_info.getTime()) {
				classToAdd = "selectable";

			}
			$("tr.weedays th").each(function () {
				var row = $(this);
				if (row.data("weekday") === weekday) {
					column = row.data("column");
					return;
				}
			});
			$($($($("tr.days").get(row)).find("td").get(column)).addClass(classToAdd + " " + currentDay).html(day));
			currentDay = "";
			if (column == 6) {
				row++;
			}
		});

	$("td.selectable").click(function () {
		dateClickHandler($(this));
		var day1 = parseInt($(this).html());
		var month1 = $("span.month").html();
		var year1 = $("span.year").html();
		var datum = day1 + '.' + month1 + '.' + year1;
		var user = $("span.user").html();
		$.getJSON('http://192.168.43.146:8080/Ereignislist/?Mail=' + user + '&Datum=' + datum, function(data) {
			if(data.ereignisse[0] == null){
				$("span.result").html('Noch kein Termin');
			}
			else if(data.ereignisse[0].startdatum == datum){
				$("span.result").html(data.ereignisse[0].name + ':' + '</br>' + data.ereignisse[0].beschreibung);
			}
			else{
				alert('fail');
			}
			});
		});
		};

function save(){
	$("td.selectable").click(function () {
			dateClickHandler($(this));
			var day1 = parseInt($(this).html());
			var month1 = $("span.month").html();
			var year1 = $("span.year").html();
			var datum = day1 + '.' + month1 + '.' + year1;
		var user = $("span.user").html();
		var inputBez = document.getElementById("bezei").value;
		var inputBes = document.getElementById("ereig").value;

		if( inputBez != "" && inputBes != ""){
		$.getJSON('http://192.168.43.146:8080/InsertTermin/?Datum='+ datum +'&Bezeichnung='+ inputBez +'&Beschreibung='+ inputBes +'&User=' + user, function(data) {
			});
			document.getElementById("ereig").value = "";
			document.getElementById("bezei").value = "";
	};
})
}
function deleteein(){
	$("td.selectable").click(function () {
			dateClickHandler($(this));
			var day1 = parseInt($(this).html());
			var month1 = $("span.month").html();
			var year1 = $("span.year").html();
			var datum = day1 + '.' + month1 + '.' + year1;

		$.getJSON('http://192.168.43.146:8080/DeleteTermin/?Datum='+ datum, function(data) {
			});
			$("span.result").html('Noch kein Termin');
	});
}
initCalender(getMonth(new Date()));

var clickCounter = 0;
$(".showDate").click(function () {
	$(".right-wrapper").toggleClass("is-active");
	$(".sideb").toggleClass("is-active");
	$(this).toggleClass("is-active");
});

function dateClickHandler(elem) {

	var day1 = parseInt($(elem).html());
	if (clickCounter === 0) {
		$("td.selectable").each(function () {
			$(this).removeClass("active between hover");
		});
	}
	clickCounter++;
	if (clickCounter === 2) {
		$("td.selectable").each(function () {
			$(this).unbind('mouseenter').unbind('mouseleave');
		});
		clickCounter = 0;
		return;
	}
	$(elem).toggleClass("active");
	$("td.selectable").hover(function () {

		var day2 = parseInt($(this).html());
		$("td.selectable").each(function () {
			$(this).removeClass("between");

		});
		if (day1 > day2 + 1) {
			$("td.selectable").each(function () {
				var dayBetween = parseInt($(this).html());
				if (dayBetween > day2 && dayBetween < day1) {
				}
			});
		} else if (day1 < day2 + 1) {
			$("td.selectable").each(function () {
				var dayBetween = parseInt($(this).html());
				if (dayBetween > day1 && dayBetween < day2) {
				}
			});
		}
	}, function () {
		$(this).removeClass("hover");
	});
}
$(".fa-angle-left").click(function () {
	getPrevMonth();
	$(".main").addClass("is-rotated-left");
	setTimeout(function () {
		$(".main").removeClass("is-rotated-left");
	}, 195);
});

$(".fa-angle-right").click(function (inputVal) {
	getNextMonth();
	$(".main").addClass("is-rotated-right");
	setTimeout(function () {
		$(".main").removeClass("is-rotated-right");
	}, 195);
});

	function getInputValue(){
						 var inputVal = document.getElementById("log-email").value;
						 var inputValPsw = document.getElementById("log-psw").value;

$.getJSON('http://192.168.43.146:8080/User/?Mail=' + inputVal, function(data) {
	if(data.passwort == inputValPsw){
		document.getElementById("id01").style.display = "none";
		$("span.user").html(data.email);

	}
	else{
		alert("Passwort falsch.");
	}

	});
};
