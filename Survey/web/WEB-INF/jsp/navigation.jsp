<!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Login Detail</a>
        </div>
        <div class="navbar-collapse collapse">
          
          <ul class="nav navbar-nav navbar-right">
            <li id="manageSurveyActive"><a href="SurveyDetailsController">Manage Survey</a></li>
            <li id="makeEmailListActive"><a href="EmailListController">Make Email List</a></li>
            <li id="aboutUsActive"><a href="../navbar/">About Us</a></li>
            <li id="logOutActive"><a href="LogOutController">LogOut</a></li>

          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    <script>
    //alert('between '+window.location);
    $(document).ready(function(){
		var winLoc=window.location+"";
		if(winLoc.indexOf("SurveyDetailsController")>0){
               $('#manageSurveyActive').addClass("active");
				}
		
		if(winLoc.indexOf("EmailListController")>0){
            $('#makeEmailListActive').addClass("active");
				}

		if(winLoc.indexOf("AboutUs")>0){
            $('#aboutUsActive').addClass("active");
				}

		if(winLoc.indexOf("LogOutController")>0){
            $('#logOutActive').addClass("active");
			}
				
        });
    </script>