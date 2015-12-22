var qfBpmCandidateUserSelectCtrl = [ '$scope','$http', 'filterFilter', function($scope, $http, filterFilter) {
	
	$scope.mySelections = [];  
    //Grid 筛选  
    $scope.filterOptions = {  
        filterText: '',  
        useExternalFilter: false  
    };  
    //总共条目  
    $scope.totalServerItems = 10;  
    $scope.filterString = '';
    //设置相关页面数据  
    /*$scope.pagingOptions = {  
        pageSizes: [10, 50, 100],//page 行数可选值  
        pageSize: 10, //每页行数  
        currentPage: 1, //当前显示页数  
    };  
    $scope.skip = 0;
    $scope.skipEnd = 0;
    $scope.users = [];*/
    //$scope.users = $scope.assignData.slice(0, 10);
    var users_list = $scope.reList_items;
    if ($scope.filter_items.length > 0) {
    	$scope.users = $scope.filter_items;
	}
    var _tempPage = angular.element('.modal .modal-content .modal-body .currentPage').val();
   	if (_tempPage && _tempPage != 'undefined' && _tempPage != null) {
    	$scope.tempPage.shift();
    	$scope.tempPage.push(_tempPage);		
   	}
    $scope.setTemp = function() {
    	$scope.filter_items.shift();    	
        $scope.filter_items =  $scope.filter_items.slice(0, $scope.filter_items.length);        
    	$scope.open_this();
    	$scope.close_this($scope);
    };

    $scope.getUsers = function(flag) {
    	var curPage = $scope.currentPage[0];
    	var startUserNum = $scope.pagingOptions.pageSize * (curPage - 1);
    	var endUserNum = $scope.pagingOptions.pageSize * (curPage);
		//$scope.users = $scope.assignData.slice($scope.skip.length, $scope.pagingOptions.pageSize + $scope.skip.length);
		$scope.users = $scope.assignData.slice(startUserNum, endUserNum);
		$scope.skip.shift();
		$scope.skipEnd.shift();
		$scope.skip.push(startUserNum);
		$scope.skipEnd.push(endUserNum);
		//$scope.skipEnd = $scope.skip.length + $scope.users.length;
		$scope.totalServerItems = $scope.users.length;
	};
	if ($scope.filterStrings[0] == '' || $scope.filterStrings[0] == null) {
		$scope.getUsers();
	}
    $scope.next = function() {
    	var tempPage = $scope.tempPage[0];
    	var curPage = $scope.currentPage[0];
		var toPage = angular.element('.modal .modal-content .modal-body .currentPage').val();	
		//alert(curPage + '/' + toPage + '/' + tempPage);
		if ($scope.users.length == $scope.pagingOptions.pageSize) {
			if (curPage == toPage && (curPage - tempPage) <= 1) {
				curPage += 1;
				$scope.currentPage.shift();	
				$scope.currentPage.push(curPage);
			}
			$scope.getUsers();
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
			$scope.getUsers('prev');
			$scope.open_this();
    		$scope.close_this($scope);
		}else {
		/*if ($scope.skip.length > 0) {
			if ($scope.skip.length >= parseInt($scope.pagingOptions.pageSize)) {
				for (var i = 0; i < parseInt($scope.pagingOptions.pageSize); i++) {
					$scope.skip.shift();
					$scope.skipEnd.shift();
				}
			}else {
				//$scope.skip.length = 0;
			}
			curPage -= 1;
			$scope.currentPage.shift();	
			$scope.currentPage.push(curPage);
			$scope.getUsers('prev');
			$scope.open_this();
    		$scope.close_this($scope);
		}*/}
		//$scope.$apply();
	};
	$scope.setFilter = function(event) {
		$scope.filterStrings.shift();
		$scope.filterStrings.push($scope.filterString.toString());
		var filterStr = angular.element('.modal .modal-content .modal-body .ngGrid #filterUser').val();	
		$scope.filterString = filterStr;
		$scope.filterStrings.shift();
		$scope.filterStrings.push(filterStr);
		$scope.temp_users = filterFilter($scope.users, $scope.filterString);
		while ($scope.users.length !== 0) {
			$scope.users.shift();
		}
		while ($scope.filter_items.length !== 0) {
			$scope.filter_items.shift();
		}
		angular.forEach($scope.temp_users, function(user) {
			//$scope.users.push(user);
			$scope.filter_items.push(user);
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
		$scope.temp_users = filterFilter($scope.users, $scope.filterString);
		while ($scope.users.length !== 0) {
			$scope.users.shift();
		}
		while ($scope.filter_items.length !== 0) {
			$scope.filter_items.shift();
		}
		angular.forEach($scope.temp_users, function(user) {
			$scope.users.push(user);
			//$scope.filter_items.push(user);
		});	
		$scope.open_this();
    	$scope.close_this($scope);    	
	};
	angular.element('.modal-body .ngSelectionCheckbox').bind('click', function() {
		alert(111);
	});
        
	$scope.gridCandidateUserOptions = { 
		data : 'users' ,
		selectedItems: $scope.mySelections,
        showSelectionCheckbox: true,
        multiSelect: true,
        showGroupPanel: false,
        showColumnMenu: true,
        enableCellSelection: true,  
        enableCellEditOnFocus: false,  
        //enablePaging: true,  
        showFooter: true,  
        totalServerItems: $scope.totalServerItems,  
        filterOptions: $scope.filterOptions,  
        //pagingOptions: $scope.pagingOptions,  
        i18n:'zh-cn',  
	   columnDefs: [{field: 'staffId', displayName: '编码'}, 
                    {field:'fullName', displayName:'姓名'}]
    };
	
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
    	
    }
	$scope.close=function(){
		$scope.$hide();
	}
	$scope.save=function(){
		if($scope.mySelections){
			$scope.property.value={};
			$scope.property.value.assignment={};
			$scope.property.value.assignment.candidateUsers=[];
			for(var i=0;i<$scope.mySelections.length;i++){
				$scope.property.value.assignment.candidateUsers.splice(i, 0, {value: $scope.mySelections[i].staffId});
			}
		}
		$scope.close();
		$scope.assignmentPopup();
	}
}];
