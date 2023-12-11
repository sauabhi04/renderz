Feature: Land on the page and check basics

	Background: 
		Given User on the Renderz landing page
	
	Scenario: Verify the logo
		Then Application logo should be visible properly
	
	Scenario: Verify the title of the page
		Then User should see the title "RenderZ (previously FIFARenderZ) - Your #1 FC Mobile Database"
		
	Scenario: User redirects to Filter Players page
		Given User clicks on Players tab
		Then User redirects to "Filter Players | RenderZ" page
		
	Scenario: User can back to Home page from any page
		Given User clicks on Players tab
		When User clicks on logo
		Then User redirects back to Home page