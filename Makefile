run_all_in_parallel:
	make clean_it set_end_point

clean_it:
	mvn clean

set_end_point:
	make -j desktop mobile

############################# DesktopView #############################
desktop:
	mvn test -Dend_point=desktop -Dbrowser=chrome -Denv=desktop_chrome

############################# TabletView #############################
tablet:
	mvn test -Dend_point=tablet -Dbrowser=firefox -Denv=tablet_firefox

############################# MobileView #############################
mobile:
	mvn test -Dend_point=mobile -Dbrowser=chrome -Denv=mobile_chrome

############################# Sauce Labs #############################
saucelabs:
	make saucelabs_single

saucelabs_single:
	make sl_windows_10_edge_14

saucelabs_parallel:
	make -j sl_windows_10_edge_14 sl_os_x_10_11_safari_10

sl_windows_10_edge_14:
	mvn test -Dend_point=saucelabs -Dsauce_os="Windows 10" -Dsauce_browser=MicrosoftEdge -Dsauce_browser_version=14.14393 -Denv=sl_windows_10_edge_14

sl_windows_7_ie_11:
	mvn test -Dend_point=saucelabs -Dsauce_os="Windows 7" -Dsauce_browser=iexplore -Dsauce_browser_version=11.0 -Denv=sl_windows_7_ie_11

sl_os_x_10_11_safari_10:
	mvn test -Dend_point=saucelabs -Dsauce_os="OS X 10.11" -Dsauce_browser=safari -Dsauce_browser_version=10.0 -Denv=-Denv=sl_os_x_10_11_safari_10

############################# BrowserStack #############################
browserstack:
	make browserstack_single

browserstack_single:
	make bs_windows_10_firefox_69

browserstack_parallel:
	make -j bs_windows_10_firefox_69 bs_windows_8.1_ie_11

bs_windows_10_firefox_69:
	mvn test -Dend_point=browserstack -Dbs_local_testing=false -Dbs_browser=Firefox -Dbs_browser_version=69.0 -Dbs_os=Windows -Dbs_os_version=10 -Dbs_selenium_version=3.141.59 -Denv=bs_windows_10_firefox_69

bs_windows_8.1_ie_11:
	mvn test -Dend_point=browserstack -Dbs_local_testing=false -Dbs_browser=IE -Dbs_browser_version=11.0 -Dbs_os=Windows -Dbs_os_version=8.1 -Dbs_selenium_version=3.141.59 -Denv=bs_windows_8.1_ie_11


