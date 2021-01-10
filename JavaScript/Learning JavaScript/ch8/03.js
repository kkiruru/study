function solution(S) {
	var fileMap = {
		'music': ['mp3', 'aac', 'flac'],
		'images': ['jpg', 'bmp', 'gif'],
		'movies': ['mp4', 'avi', 'mkv'],
	};

	var partitionData = getPartitionData(S);
	var calcSize = calcSize(partitionData);
	var convertString = convertString(calcSize);
	function getPartitionData(str) {
		var data = [];
		var splitStr = str.split("\n");
		var splitData = [];
		for (var i = 0; i < splitStr.length; i++) {
			splitData = splitStr[i].split(" ");
			var file = splitData[0];
			var splitFile = file.split(".");
			var fileName = splitFile[0];
			var extension = splitFile.length > 0 ?
				splitFile[splitFile.length - 1] : splitFile[1];
			var type = getFileTyle(extension);
			var size = splitData[1];
			data.push({
				'fileName': fileName,
				'extension': extension,
				'size': size,
				'type': type
			});
		}
		return data;
	}

	function getFileTyle(extension) {
		var result = "other";
		var keys = Object.keys(fileMap);
		var data = [];
		var type = "";
		for (var i = 0; i < keys.length; i++) {
			type = keys[i];
			data = fileMap[type];
			for (var j = 0; j < data.length; j++) {
				if (data[j] === extension) {
					result = type;
				}
			}
		}
		return result;
	}

	function calcSize(data) {
		var result = {
			'music': 0,
			'images': 0,
			'movies': 0,
			'other': 0
		};
		var keys = Object.keys(fileMap);
		for (var i = 0; i < data.length; i++) {
			result[data[i].type] +=
				parseInt(data[i].size);
		}
		return result;
	}

	function convertString(data) {
		var result = "";
		var keys = Object.keys(data);
		var key, value;
		for (var i = 0; i < keys.length; i++) {
			key = keys[i];
			value = data[key];
			result += keys[i] + " " + data[key] + "b"
			if (i !== keys.length - 1) {
				result += "\n";
			}

		}
		return result;
	}
	return convertString;
}