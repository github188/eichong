$(function(){
  var Ctor=new Vue({
    el:'#app',
    data:{
        dataObject: [],
        selectObject: [],
        companyArray:[],
        provinceCode:"",
        cityCode:"",
        cpyId:"",
        type:"",
        disabled:''
    },
    methods: {
      handleChange: function(event){
        this.companyArray = event;
        this.cpyId = this.companyArray[2];
        if(this.cpyId == undefined){
          window.localStorage.setItem("cpyId","");
        }else{
          window.localStorage.setItem("cpyId",this.cpyId);
        }
      },
      clearLocalStorage:function(){
        window.localStorage.setItem('cityCode', '');
        window.localStorage.setItem('provinceCode', '');
        window.localStorage.setItem('powerstationId', '');
        window.localStorage.setItem('cpyId', '');
      },
      getChangeValue:function(event){
        //地图层级判断
        map.setZoom(5);
        map.clearMap();
        getElectricPileMap(this.provinceCode,this.cityCode,this.cpyId,2);
      },
      getData:function(event){//下拉框接口
        var _this = this;
        $.ajax({
          type: "get",
          url: basePath + getSelectScopeUrl,
          async: false,
          success: function (data) {
            _this.dataObject = data.dataObject;
            window.localStorage.setItem('dataObject',JSON.stringify(_this.dataObject));
          }
        })
      },
      getDataObject:function(){
        var dataObject=window.localStorage.getItem('dataObject');
        if(!dataObject||dataObject==undefined){
          this.getData();
        }else{
          this.dataObject=JSON.parse(dataObject);
        }
      },
      getCookie:function(event){
        var loginUser=window.localStorage.getItem('loginUser');
        var selectCode=JSON.parse(loginUser).code;
        var cpyId=JSON.parse(loginUser).cpyId;
        var reArray=[];
        var newArray=[];

        this.selectObject=newArray;
        if(this.selectObject[2] == undefined){
          window.localStorage.setItem('cpyId','');
        }else{
          window.localStorage.setItem('cpyId',this.selectObject[2]);
        }

        if(!selectCode){
          this.selectObject=[];
          this.disabled=false;
          getElectricPileMap('','',this.cpyId,2);
        }else{
          reArray=selectCode.split(':');
          for(var i=0;i<reArray.length;i++){
            newArray.push(reArray[i]);
          }
          newArray.push(cpyId);
          this.disabled=true;
          getElectricPileMap('','',this.selectObject[2],2);
        }
      }
    },
    mounted: function(){
      this.clearLocalStorage();
      this.getDataObject();
      this.getCookie();
    }
  })
})