<?php

	// Database verbindingen configueren.
	$hostname = "localhost";
	$db_user = "";
	$db_pass = "";
	$db_name = ";

	// Connectie met de database
	$connection = mysqli_connect($hostname, $db_user, $db_pass, $db_name);
	if (mysqli_connect_errno()){
		die("Error connecting to the db");
	}

?>
<!DOCTYPE html>
<html lang="en">
	<head>

		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

		<!-- Fonts Awesome -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/js/all.min.js" integrity="sha512-cyAbuGborsD25bhT/uz++wPqrh5cqPh1ULJz4NSpN9ktWcA6Hnh9g+CWKeNx2R0fgQt+ybRXdabSBgYXkQTTmA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

		<!-- ChartJS -->
		<script src = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js"></script>

		<title>Dharma. Scrumboard</title>

		<!-- Custom external CSS -->
		<link rel="stylesheet" href="CSS/style.css">


	</head>
	<body>

		<div>
			<div class="row">

				<!-- Online block -->
				<div class="col-sm-3 styled-b">

					<h2>Online</h2>

						<h3><i class="fas fa-check-circle"></i> Stephane</h3>
						<h3><i class="fas fa-check-circle"></i> Selma</h3>
						<h3><i class="fas fa-check-circle"></i> Anouar</h3>
						<h3><i class="fas fa-times-circle"></i> Jesse</h3>
						<h3><i class="fas fa-times-circle"></i> Jelle</h3>

					<br>

					<h2>Links</h2>

						<h3><a href="https://huteam01.atlassian.net/rest/api/3/search?jql=sprint in openSprints()" target="_blank"><i class="fas fa-link"></i> Jira</a></h3>

				</div>

				<!-- Center -->
				<div class="col-sm-8">

					<h1>Dharma.</h1>

					<h3>Scrum dashboard</h3>

					<!-- User Stories phases, Burndown chart & Velocity chart -->
					<div class="row">

						<!-- To Do -->
						<div class="col-sm-2 text-center styled-b">
							
							<p>To Do</p>
							<h1>
							<?php
							
							// Aantal To Do ophalen uit database
								$query = "SELECT * FROM issues WHERE status = 'Nog doen'";
								$run = mysqli_query($connection, $query);
								$rows = mysqli_num_rows($run);
								
							// Output
								echo $rows;
							
							?>
							</h1>
							
						</div>
						
						<!-- In Progress -->
						<div class="col-sm-2 text-center styled-b">
							
							<p>In Progress</p>
							<h1>
							<?php
							
							// Aantal In Progress ophalen uit database
								$query = "SELECT * FROM issues WHERE status = 'Progress'";
								$run = mysqli_query($connection, $query);
								$rows = mysqli_num_rows($run);
								
							// Output
								echo $rows;
							
							?>
							</h1>
							
						</div>
						
						<!-- PR -->
						<div class="col-sm-2 text-center styled-b">
							
							<p>PR</p>
							<h1>
							<?php
							
							// Aantal PR ophalen uit database
								$query = "SELECT * FROM issues WHERE status = 'PR'";
								$run = mysqli_query($connection, $query);
								$rows = mysqli_num_rows($run);
								
							// Output
								echo $rows;
							
							?>
							</h1>
							
						</div>
						
						<!-- Testing -->
						<div class="col-sm-2 text-center styled-b">
							
							<p>Testing</p>
							<h1>
							<?php
							
							// Aantal Testing ophalen uit database
								$query = "SELECT * FROM issues WHERE status = 'Testing'";
								$run = mysqli_query($connection, $query);
								$rows = mysqli_num_rows($run);
								
							// Output
								echo $rows;
							
							?>
							</h1>
							
						</div>
						
						<!-- Done -->
						<div class="col-sm-2 text-center styled-b">
							
							<p>Done</p>
							<h1>
							<?php
							
							// Aantal Done ophalen uit database
								$query = "SELECT * FROM issues WHERE status = 'Gereed'";
								$run = mysqli_query($connection, $query);
								$rows = mysqli_num_rows($run);
								
							// Output
								echo $rows;
							
							?>
							</h1>

						</div>

						<!-- Grafiek 1 -->
						<style>
								.progress {
									width: 310px;
									height: 50px;
									background: #9cbab4;
								}
								.progress__fill{
									width: 50%;
									height: 100%;
									background: #009579
								}
						</style>

						<h3>Project Voortgang</h3>
						<div class="col-sm-5 ">
							<div class="progress">
								<div class="progress__fill"></div>
								<span class="progress__text">50%

								</span></div>
						</div>

						<!-- Grafiek 2 -->
						<div class="col-sm-6 styled-b">

							<canvas id="velocityChart"></canvas>
							<script type="text/javascript" src="JS/velocityChart.js"></script>
						</div>
				</div>

			</div>
          <!-- Grafiek 3 -->
				<div class="col-sm-7 styled-b">
					<canvas id="burndownChart"></canvas>
					<script type="text/javascript" src="JS/burndownChart.js"></script>
				</div>

				<!-- Grafiek 4 -->
				<div class="col-sm-4 styled-b">

					<canvas id="pieChart"></canvas>
					<script type="text/javascript" src="JS/pieChart.js"></script>
				</div>



			</div>


			<div>
			</div>
		</div>

		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>


	</body>
</html>
