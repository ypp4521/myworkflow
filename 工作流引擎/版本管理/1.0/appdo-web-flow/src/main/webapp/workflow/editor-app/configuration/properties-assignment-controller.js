/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Assignment
 */
var KisBpmAssignmentCtrl = [ '$scope', '$modal','$http', function($scope, $modal,$http) {


    // Config for the modal window
	$scope.assignmentPopup=function(){
		var opts = {
                template:  'editor-app/configuration/properties/assignment-popup.html?version=' + Date.now(),
		        scope: $scope
		    };
            $modal(opts);
		     //$modal.open(opts);
	}
    $scope.openAssignment=function(){
    	$http.get(ACTIVITI.CONFIG.contextRoot+"/userGroup/assignmentList?page=1&rows=20")
        .success(function(response) {
        	$scope.assignData = response.rows;
        	var assign = {
    	        template:  'editor-app/configuration/properties/assignment-select.html?version=' + Date.now(),
    	        scope: $scope
    	    };
            $modal(assign);
        	//$modal.open(assign);
        });
    }
    /**暂时屏蔽分页设置
    $scope.openCandidateUser=function(){
    	$http.get(ACTIVITI.CONFIG.contextRoot+"/userGroup/candidateUserList?page=1&rows=20")
        .success(function(response) {
        	$scope.assignData = response.rows;
        	var assign = {
    	        template:  'editor-app/configuration/properties/candidateUser-select.html?version=' + Date.now(),
    	        scope: $scope
    	    };
        	 $modal(assign);
        });
    }
    **/
    $scope.openCandidateUser=function(){
    	$http.get(ACTIVITI.CONFIG.contextRoot+"/userGroup/candidateUserListAll?page=1&rows=20")
        .success(function(response) {
            // $scope.items = ['item1', 'item2', 'item3'];
            $scope.filter_items = [];
            $scope.reList_items = response.rows;
            $scope.selected = {  
                filter_item: $scope.filter_items[0]  
            };
            $scope.users = [];
            $scope.currentPage = [1];
            $scope.tempPage = [$scope.currentPage[0]];
            $scope.pagingOptions = {  
                pageSizes: [10, 50, 100],//page 行数可选值  
                pageSize: 10, //每页行数  
                currentPage: 1, //当前显示页数  
            };  
            $scope.skip = [0];
            $scope.skipEnd = [0];
            $scope.filterStrings = [''];
            //$scope.filterString = $scope.filterStrings[0];
            $scope.close_this = function(scope){
                scope.close();
                //scope.openCandidateUser();
            };
        	$scope.assignData = response.rows;                      
            $scope.open_this = function() {                                     
                $modal(assign);
            };       
        	var assign = {
                template:  'editor-app/configuration/properties/candidateUser-select.html?version=' + Date.now(),
    	        //template:  'editor-app/configuration/properties/candidateUser-select.html?version=' + Date.now(),
    	        scope: $scope      
    	    };
        	$modal(assign);
        });
    }
    $scope.openCandidateGroup=function(){
    	$http.get(ACTIVITI.CONFIG.contextRoot+"/userGroup/candidateGroupList?page=1&rows=20")
        .success(function(response) {
            $scope.this_selections = [];
            $scope.filter_items = [];
            $scope.reList_items = [];
            $scope.selected = {  
                filter_item: $scope.filter_items[0]  
            };
            $scope.currentPage = [1];
            $scope.tempPage = [$scope.currentPage[0]];
            $scope.pagingOptions = {  
                pageSizes: [10, 50, 100],//page 行数可选值  
                pageSize: 10, //每页行数  
                currentPage: 1, //当前显示页数  
            };
            $scope.skip = [0];
            $scope.skipEnd = [0];
            $scope.filterStrings = [''];
            $scope.groups = [];
        	$scope.assignData = response.rows;
            $scope.close_this = function(scope){
                scope.close();
                //scope.openCandidateUser();
            };
            $scope.open_this = function() {                                     
                $modal(assign);
            };
        	var assign = {
    	        template:  'editor-app/configuration/properties/candidateGroup-select.html?version=' + Date.now(),
    	        scope: $scope
    	    };
            $modal(assign);
        	//$modal.open(assign);
        });
    }
    // Open the dialog
    $scope.assignmentPopup();
}];

var KisBpmAssignmentPopupCtrl = [ '$scope', function($scope) {
    
    // Put json representing assignment on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.assignment !== undefined
        && $scope.property.value.assignment !== null) 
    {
        $scope.assignment = $scope.property.value.assignment;
    } else {
        $scope.assignment = {};
    }

    if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0)
    {
    	$scope.assignment.candidateUsers = [{value: ''}];
    }else{
    	$scope.candidateUsers="";
    	for(var i=0;i<$scope.assignment.candidateUsers.length;i++){
    		if($scope.candidateUsers!=""){
    			$scope.candidateUsers+=",";
    		}
    		$scope.candidateUsers+=$scope.assignment.candidateUsers[i].value;
    	}
    }
    if($scope.assignment.candidateGroups){
    	$scope.candidateGroups="";
    	for(var i=0;i<$scope.assignment.candidateGroups.length;i++){
    		if($scope.candidateGroups!=""){
    			$scope.candidateGroups+=",";
    		}
    		$scope.candidateGroups+=$scope.assignment.candidateGroups[i].value;
    	}
    }
    
    // Click handler for + button after enum value
    var userValueIndex = 1;
    $scope.addCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index + 1, 0, {value: 'value ' + userValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index, 1);
    };
    
    if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0)
    {
    	$scope.assignment.candidateGroups = [{value: ''}];
    }
    
    var groupValueIndex = 1;
    $scope.addCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index + 1, 0, {value: 'value ' + groupValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index, 1);
    };

    $scope.save = function() {

        $scope.property.value = {};
        handleAssignmentInput($scope);
        $scope.property.value.assignment = $scope.assignment;
        
        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    // Close button handler
    $scope.close = function() {
    	handleAssignmentInput($scope);
    	$scope.property.mode = 'read';
    	$scope.$hide();
    };
    
    $scope.assignmentSelect= function(){
    	$scope.close();
    	$scope.openAssignment();
    }
    $scope.candidateUsersSelect= function(){
    	if($scope.candidateUsersSel){
    		$scope.close();
        	$scope.openCandidateUser();
    	}
    }
    $scope.candidateGroupsSelect=function(){
    	$scope.close();
    	$scope.openCandidateGroup();
    }
    
    var handleAssignmentInput = function($scope) {
    	if ($scope.assignment.candidateUsers)
    	{
	    	var emptyUsers = true;
	    	var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateUsers.length; i++)
	        {
	        	if ($scope.assignment.candidateUsers[i].value != '')
	        	{
	        		emptyUsers = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyUsers)
	        {
	        	//$scope.assignment.candidateUsers = undefined;
	        }
	        if($scope.assignment.candidateUsers&&$scope.candidateUsers){
	        	$scope.assignment.candidateUsers = [{value: $scope.candidateUsers}];
	        }
    	}
    	if ($scope.assignment.candidateGroups)
    	{
	        var emptyGroups = true;
	        var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateGroups.length; i++)
	        {
	        	if ($scope.assignment.candidateGroups[i].value != '')
	        	{
	        		emptyGroups = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyGroups)
	        {
	        	//$scope.assignment.candidateGroups = undefined;
	        }
	        if($scope.assignment.candidateGroups&&$scope.candidateGroups){
	        	$scope.assignment.candidateGroups = [{value: $scope.candidateGroups}];
	        }
    	}
    };
}];