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
      mongoliaLayer: function(event){
        $('#mongoliaLayer').css('display','block');
      },
      handleChange: function(event){
        this.companyArray = event;

        this.cpyId = this.companyArray[2];
        if(this.cpyId == undefined){
          window.localStorage.setItem("cpyId","");
        }else{
          window.localStorage.setItem("cpyId",this.cpyId);
        }

        if(this.cpyId){
          $('#mongoliaLayer').css('display','none');
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
        this.cpyId = window.localStorage.getItem('cpyId');
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
          $('#queryBtn').removeAttr("disabled");
          getElectricPileMap('','',this.cpyId,2);
        }else{
          $('#queryBtn').attr("disabled",true).css({
            'background':'#ccc'
          });
          reArray=selectCode.split(':');
          for(var i=0;i<reArray.length;i++){
            newArray.push(reArray[i]);
          }
          newArray.push(cpyId);
          this.disabled=true;
          getElectricPileMap('','',this.selectObject[2],2);
        }
      },
      dropdownBox:function(){//蒙层消失
        $('#mongoliaLayer').on('click',function(){
          $('#mongoliaLayer').css('display','none');
        })
      }
    },
    mounted: function(){
      this.clearLocalStorage();
      this.getDataObject();
      this.getCookie();
      this.dropdownBox();
    }
  })
})