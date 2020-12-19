<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>天气预报</title>
    <script src="js/echarts.min.js"></script>
    <link rel="stylesheet" href="css/tq.css" />
</head>
<body>
<div class="a0"><!--大盒子-->
        <div class="a1"><!--搜索栏-->
            <form  action="WeatherServlet" method ="post" >
                <input class="a11" value="请输入城市名称" onfocus="cls()" onblur="res()" name="city"/>
                <input type="submit" class="a12" value="" style="background-color:skyblue"/>
            </form>
        </div>
        <div class="a2"><!--主体-->
            <div class="a21"><!--左边部分-->
                <div>
                    <table class="t1" width="500" height="225">
                        <tr><!--地点详情-->
                            <td height="111">
                                <p class="t1-1">${c0}</p>
                                <div class="t1-2">
                                	<script  type="text/JavaScript" src="js/navCal.js"></script>
                                </div>
                            </td>
                        </tr>
                        <tr><!--天气梗概-->
                            <td height="100">
                                <img class="t1-0" src=${c39} />
                                <div class="t1-3">${c2}</div>
                                <div class="t1-4">℃</div>
                                <div class="t1-5">
                                    <p>${c1}</p>
                                </div>

                            </td>
                        </tr>
                        <tr><!--适度风向紫外线-->
                            <td height="36">
                                <p class="t1-7">湿度：${c3}%&nbsp;风向：${c4}&nbsp;${c5}&nbsp;</p>
                            </td>
                        </tr>
                        <tr><!--空气质量日出日落-->
                            <td height="77">
                                <div class="t1-8">空气质量：${c7}</div>
                                <div class="t1-9">PM：${c6}</div>
                            </td>
                        </tr>
                    </table>
                </div>

            </div>
            <div class="a22"><!--右边部分-->
                <p>${c0}未来五天天气预报</p>
                <div class="a22-1">
                    <table class="t2" width="500" height="300" border="1">
                        <tr>
                            <td>${c8}</td>
                            <td>${c12}</td>
                            <td>${c16}</td>
                            <td>${c20}</td>
                            <td>${c24}</td>
                            
                        </tr>
                        <tr>
                            <td>${c28}</td>
                            <td>${c29}</td>
                            <td>${c30}</td>
                            <td>${c31}</td>
                            <td>${c32}</td>
                            
                        </tr>
                        <tr>
                            <td><img src= ${c9} /></td>
                            <td><img src= ${c13} /></td>
                            <td><img src= ${c17} /></td>
                            <td><img src= ${c21} /></td>
                            <td><img src= ${c25} /></td>
                            

                        </tr>
                        <tr class="t2-1">
                            <td height="35">${c10}</td>
                            <td>${c14}</td>
                            <td>${c18}</td>
                            <td>${c22}</td>
                            <td>${c26}</td>
                            
                        </tr>
                        <tr>
                            <td colspan="7">
                                <div id="main"></div>
                            </td>
                        </tr>
                        <tr>
                            <td height="50">${c11}</td>
                            <td>${c15}</td>
                            <td>${c19}</td>
                            <td>${c23}</td>
                            <td>${c27}</td>
                            

                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'));       
        var temp1 = "${c33}";
        var temp2 = "${c34}";
        var temp3 = "${c35}";
        var temp4 = "${c36}";
        var temp5 = "${c37}";
        
        var c1 = parseInt(temp1,10);
        var c2 = parseInt(temp2,10);
        var c3 = parseInt(temp3,10);
        var c4 = parseInt(temp4,10);
        var c5 = parseInt(temp5,10);
        
        var option = {
            grid:{
                x:0,
                y:30,
                x2:0,
                y2:0,
                borderWidth:15
            },
            xAxis: {
                type: 'category',
                data: [],
                axisTick: {
                    show: false //是否显示刻度
                },
                axisLine: {
                    show: false
                },
                axisLabel: {
                    show: false
                }
            },
            yAxis: {
                type: 'value',
                axisTick: {
                    show: false //是否显示刻度
                },
                splitLine: {
                    show: false
                },
                axisLabel: {
                    show: false
                }
            },
            series: [{
                data: [c1,c2,c3,c4,c5],
                type: 'line',
                smooth: true,
                itemStyle: { //拐点标记的样式
                    normal: {
                        color: '#ffffff',
                        barBorderRadius: 8,
                        label: {
                            show: true, //开启显示
                            position: 'top', //在上方显示
                            formatter: function (params, ticket) {

                            },
                            textStyle: { //数值样式
                                color: '#ffffff',
                                fontSize: 16
                            }
                        }
                    }

                }

            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        function cls(){
            //捕获触发事件的对象，并设置为以下语句的默认对象
            with(event.srcElement)
                //如果当前值为默认值，则清空
                if(value==defaultValue){
                    value=""
                }
        }
        function res(){
            //捕获触发事件的对象，并设置为以下语句的默认对象
            with(event.srcElement)
                //如果当前值为空，则重置为默认值
                if(value==""){
                    value=defaultValue
                }
        
       
        
        }
    </script>
</body>
</html>