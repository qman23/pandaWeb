<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />


<link href="../style/css/font-awesome.css" rel="stylesheet" />
<link href="../style/css/adminia.css" rel="stylesheet" /> 
<link href="../style/css/adminia-responsive.css" rel="stylesheet" /> 
<script>
</script>
<script src="../style/js/charts/bar.js"></script>
<script src="../style/js/charts/area.js"></script>
<script src="../style/js/charts/line.js"></script>
<script src="../style/js/charts/pie.js"></script>
<script src="../style/js/charts/excanvas.min.js"></script>
<script src="../style/js/charts/jquery.flot.js"></script>
<script src="../style/js/charts/jquery.flot.pie.js"></script>
<script src="../style/js/charts/jquery.flot.orderBars.js"></script>
<script src="../style/js/charts/jquery.flot.resize.js"></script>		
</head>
<body>
<div class="col-md-10 main">
			<div class="widget">
					<div class="widget-header">
						<h3>Area Chart</h3>
					</div> <!-- /widget-header -->								
					<div class="widget-content">
						<div id="area-chart" class="chart-holder"></div> <!-- /area-chart -->							
					</div> <!-- /widget-content -->				
				</div> <!-- /widget -->		
					<div class="widget">	
					<div class="widget-header">
						<h3>Line Chart</h3>
					</div> <!-- /widget-header -->												
					<div class="widget-content">		
						<div id="line-chart" class="chart-holder"></div> <!-- /donut-chart -->								
					</div> <!-- /widget-content -->	
				</div> <!-- /widget -->
				<div class="widget">
					<div class="widget-header">
						<h3>Bar Chart</h3>
					</div> <!-- /widget-header -->		
					<div class="widget-content">
						<div id="bar-chart" class="chart-holder"></div> <!-- /donut-chart -->			
					</div> <!-- /widget-content -->
				</div> <!-- /widget -->
	
				<div class="widget">
					<div class="widget-header">
						<h3>Pie Chart</h3>
					</div> <!-- /widget-header -->									
					<div class="widget-content">
						<div id="pie-chart" class="chart-holder"></div> <!-- /donut-chart -->					
					</div> <!-- /widget-content -->
				</div> <!-- /widget -->
</div>
	
</body>
</html>