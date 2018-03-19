/**
 * @requires jquery.validate.js
 * @author ZhangHuihua@msn.com
 */
(function($){
	if ($.validator) {
		$.validator.addMethod("normal", function(value, element) {
			var reg = RegExp(/[(\ )(\`)(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\+)(\=)(\|)(\{)(\})(\')(\:)(\;)(\')(',)(\[)(\])(\.)(\<)(\>)(\/)(\?)(\~)(\！)(\@)(\#)(\￥)(\%)(\…)(\&)(\*)(\（)(\）)(\—)(\+)(\|)(\{)(\})(\【)(\】)(\‘)(\；)(\：)(\”)(\“)(\’)(\。)(\，)(\、)(\？)]+/);   
			return this.optional(element) || !reg.test(value);
		}, "不能输入特殊字符！");
		$.validator.addMethod("special", function(value, element) {
			var reg = RegExp(/[\`\~\!\@\#\$\%\^\&\*\+\=\|\{\}\'\:\;\'',\[\]\.\<\>\/\?\~\！\@\#\￥\%\…\&\*\（\）\—\+\|\{\}\【\】\‘\；\：\”\“\’\。\，\、\？]+/);   
			return this.optional(element) || !reg.test(value);
		}, "不能输入特殊字符！");
		$.validator.addMethod("alphanumeric", function(value, element) {
			return this.optional(element) || /^\w+$/i.test(value);
		}, "Letters, numbers or underscores only please");
		
		$.validator.addMethod("halfsymbol", function(value, element) {
			return this.optional(element) || /^[0-9 A-Za-z \x00-\xff]+$/i.test(value);
		}, "Letters, numbers or symbol only please");
		
		$.validator.addMethod("lettersonly", function(value, element) {
			return this.optional(element) || /^[a-z]+$/i.test(value);
		}, "Letters only please"); 
		
		$.validator.addMethod("phone", function(value, element) {
			return this.optional(element) || /^[0-9 \(\)]{11,30}$/.test(value);
		}, "Please specify a valid phone number");
		
		$.validator.addMethod("bigPassword", function(value, element) {
			return this.optional(element) || /^[0-9 \(\)]{6,30}$/.test(value);
		}, "Please specify a valid phone number");
		
		$.validator.addMethod("userAccount", function(value, element) {
			return this.optional(element) || /^[0-9a-zA-Z]*$/g.test(value);
		}, "Please specify a valid userAccount number");
		
		$.validator.addMethod("partnerName", function(value, element) {
			return this.optional(element) || /^[0-9]{4}$/g.test(value);
		}, "Please specify a valid partnerName number");
		
		$.validator.addMethod("ipvalidator", function(value, element) {
			return this.optional(element) || /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(value);
		}, "Please specify a valid ip number");
		
		$.validator.addMethod("cert", function(value, element) {
			return this.optional(element) || /(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(value);
		}, "Please specify a valid cert number");
		
		
		$.validator.addMethod("postcode", function(value, element) {
			return this.optional(element) || /^[0-9 A-Za-z]{5,20}$/.test(value);
		}, "Please specify a valid postcode");
		
		$.validator.addMethod("decimal", function(value, element) {
			return this.optional(element) || /^\+?(\d*\.\d{2})$/.test(value);
		}, "Please specify a valid postcode");
		
		$.validator.addMethod("date", function(value, element) {
			value = value.replace(/\s+/g, "");
			if (String.prototype.parseDate){
				var $input = $(element);
				var pattern = $input.attr('dateFmt') || 'yyyy-MM-dd';
	
				return !$input.val() || $input.val().parseDate(pattern);
			} else {
				return this.optional(element) || value.match(/^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/);
			}
		}, "Please enter a valid date.");
		
		/*自定义js函数验证
		 * <input type="text" name="xxx" customvalid="xxxFn(element)" title="xxx" />
		 */
		$.validator.addMethod("customvalid", function(value, element, params) {
			try{
				return eval('(' + params + ')');
			}catch(e){
				return false;
			}
		}, "Please fix this field.");
		
		$.validator.addClassRules({
			date: {date: true},
			alphanumeric: { alphanumeric: true },
			lettersonly: { lettersonly: true },
			phone: { phone: true },
			postcode: {postcode: true}
		});
		$.validator.setDefaults({errorElement:"span"});
		$.validator.autoCreateRanges = true;
		
	}

})(jQuery);