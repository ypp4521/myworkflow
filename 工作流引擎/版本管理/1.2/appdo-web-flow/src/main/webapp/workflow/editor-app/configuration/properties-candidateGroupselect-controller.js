var qfBpmCandidateGroupSelectCtrl = [ '$scope','$http', 'filterFilter', function($scope, $http, filterFilter) {
	$scope.mySelections = [];  
    //Grid 筛选  
    $scope.filterOptions = {  
        filterText: '',  
        useExternalFilter: false  
    };  
    //总共条目  
    $scope.totalServerItems = 10;  
    $scope.filterString = '';
    /*//设置相关页面数据  
    $scope.pagingOptions = {  
        pageSizes: [20, 50, 100],//page 行数可选值  
        pageSize: 20, //每页行数  
        currentPage: 1, //当前显示页数  
    };  */
    var groups_list = $scope.reList_items;
    if ($scope.filter_items.length > 0) {
    	$scope.groups = $scope.filter_items;
	}
    var _tempPage = angular.element('.modal .modal-content .modal-body .currentPage').val();
   	if (_tempPage && _tempPage != 'undefined' && _tempPage != null) {
    	$scope.tempPage.shift();
    	$scope.tempPage.push(_tempPage);		
   	}
   	$scope.getGroups = function(flag) {
    	var curPage = $scope.currentPage[0];
    	var startUserNum = $scope.pagingOptions.pageSize * (curPage - 1);
    	var endUserNum = $scope.pagingOptions.pageSize * (curPage);
		//$scope.groups = $scope.assignData.slice($scope.skip.length, $scope.pagingOptions.pageSize + $scope.skip.length);
		$scope.groups = $scope.assignData.slice(startUserNum, endUserNum);
		$scope.skip.shift();
		$scope.skipEnd.shift();
		$scope.skip.push(startUserNum);
		$scope.skipEnd.push(endUserNum);
		$scope.totalServerItems = $scope.groups.length;
	};
	if ($scope.filterStrings[0] == '' || $scope.filterStrings[0] == null) {
		$scope.getGroups();
	}
	$scope.next = function() {
    	var tempPage = $scope.tempPage[0];
    	var curPage = $scope.currentPage[0];
		var toPage = angular.element('.modal .modal-content .modal-body .currentPage').val();	
		//alert(curPage + '/' + toPage + '/' + tempPage);
		if ($scope.groups.length == $scope.pagingOptions.pageSize) {
			if (curPage == toPage && (curPage - tempPage) <= 1) {
				curPage += 1;
				$scope.currentPage.shift();	
				$scope.currentPage.push(curPage);
			}
			$scope.getGroups();
			$scope.open_this();
    		$scope.close_this($scope);
		}
	};
	$scope.prev = function() {
		var curPage = $scope.currentPage[0];
		if (curPage > 1) {
			curPage -= 1;
			$scope.currentPage.shift();	
			$scope.currentPage.push(curPage);
			$scope.getGroups('prev');
			$scope.open_this();
    		$scope.close_this($scope);
		}
	};
	$scope.setFilter = function(event) {
		$scope.filterStrings.shift();
		$scope.filterStrings.push($scope.filterString.toString());
		var filterStr = angular.element('.modal .modal-content .modal-body .ngGrid #filterGroup').val();	
		$scope.filterString = filterStr;
		$scope.filterStrings.shift();
		$scope.filterStrings.push(filterStr);
		$scope.temp_groups = filterFilter($scope.groups, $scope.filterString);
		while ($scope.groups.length !== 0) {
			$scope.groups.shift();
		}
		while ($scope.filter_items.length !== 0) {
			$scope.filter_items.shift();
		}
		angular.forEach($scope.temp_groups, function(group) {
			//$scope.groups.push(group);
			$scope.filter_items.push(group);
		});	
		$scope.open_this();
    	$scope.close_this($scope);
	};
	$scope.reList = function() {
		$scope.filterStrings.shift();
		$scope.filterStrings.push($scope.filterString.toString());
		var filterStr = '';	
		$scope.filterString = filterStr;
		$scope.filterStrings.shift();
		$scope.filterStrings.push(filterStr);
		$scope.temp_groups = filterFilter($scope.groups, $scope.filterString);
		while ($scope.groups.length !== 0) {
			$scope.groups.shift();
		}
		while ($scope.filter_items.length !== 0) {
			$scope.filter_items.shift();
		}
		angular.forEach($scope.temp_groups, function(group) {
			$scope.groups.push(group);
			//$scope.filter_items.push(group);
		});	
		$scope.open_this();
    	$scope.close_this($scope);
	};
	$scope.gridCandidateGroupOptions = { data : 'groups' ,
							selectedItems: $scope.mySelections,  
				            showSelectionCheckbox: true,  
				            multiSelect: true,  
				            showGroupPanel: false,  
				            showColumnMenu: true,  
				            //enableCellSelection: false,  
				            enableCellEditOnFocus: false,  
				            enableRowSelection: true,
				            keepLastSelected: true,
				            //enablePaging: true,  
				            showFooter: true,  
				            totalServerItems: $scope.totalServerItems,  
				            //filterOptions: $scope.filterOptions,  
				            //pagingOptions: $scope.pagingOptions,  
				            i18n:'zh-cn',  				        
						   columnDefs: [{field: 'dtpId', displayName: '编码'}, 
					                    {field:'dptName', displayName:'名称'}]};

	$scope.$watch('pagingOptions', function () {  
		$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);  
    },true);  
    $scope.$on('ngGridEventData', function(data){
        //$scope.gridCandidateGroupOptions.selectRow(0, true);
    });
    angular.element('.modal .modal-content .modal-body .ngGrid .resetButton').on('click', function() {
    	alert(123455);
    });
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
    	
    }
	$scope.close=function(){
		$scope.$hide();
	}
	$scope.save=function(){
		if($scope.mySelections){
			$scope.property.value={};
			$scope.property.value.assignment={};
			$scope.property.value.assignment.candidateGroups=[];
			for(var i=0;i<$scope.mySelections.length;i++){
				alert("$scope.mySelections[i].dptId"+ " = " + $scope.mySelections[i].dptId + "\n$scope.mySelections[i].dptName = " + $scope.mySelections[i].dptName);
				$scope.property.value.assignment.candidateGroups.splice(i, 0, {value: $scope.mySelections[i].dptId});
			}
		}
		$scope.close();
		$scope.assignmentPopup();
	}
}];