function DomainOrgLocale(domainSel, orgSel){
	self = this;
	this.domainSel = domainSel;
	this.orgSel = orgSel;
	var c_name = {
			loginName:"登录名(loginName)",
			userName:"用户名(userName)",
			loginPass:"密码(loginPass)",
			domainId:"域ID(domainId)",
			groupName:"组名(groupName)",
			personId:"组织机构中人员ID(personId)",
			groupId:"组ID(groupId)",
			userPhone:"手机号(userPhone)",
			mobileNo:"手机号(mobileNo)",
			userSex:"性别(userSex)",
			userEmail:"邮箱(userEmail)",
			email:"邮箱(email)",
			avatorLoc:"头像(avatorLoc)",
			orgid:"组织ID(orgid)",
			orgId:"组织ID(orgId)",
			orgName:"组织名称(orgName)",
			dwcompany:"所属公司(dwcompany)",
			name:"姓名(name)",
			description:"描述(description)",
			officeLoc:"办公地点(officeLoc)",
			officePhone:"座机(officePhone)",
			ipPhone:"ip电话(ipPhone)",
			country:"国家(country)",
			province:"省份(province)",
			city:"城市(city)",
			street:"街道(street)",
			postcode:"邮政编码(postcode)",
			position:"地理位置(position)",
			jobNumber:"工号(jobNumber)",
			company:"所属单位(company)",
			leader:"上级(leader)",
			authCode:"认证码(authCode)",
			uniqueField:"用户标识(uniqueField)",
			field1:"扩展字段1(field1)",
			field2:"扩展字段2(field2)",
			field3:"扩展字段3(field3)",
			field4:"扩展字段4(field4)",
			field5:"扩展字段5(field5)",
			field6:"扩展字段6(field6)",
			field7:"扩展字段7(field7)",
			field8:"扩展字段8(field8)",
			field9:"扩展字段9(field9)",
			field10:"扩展字段10(field10)"
	};

	self.initDomainName = function(selector){
		$(selector).each(function(index, item){
			$(item).html(c_name[$(item).val()]);
		});
	};
	
	self.initOrgName = function(selector){
		$(selector).each(function(index, item){
			$(item).html(c_name[$(item).val()]);
		});
	};
	
	(function(me){
		me.initDomainName(me.domainSel);
		me.initOrgName(me.orgSel);
	})(self);
}
