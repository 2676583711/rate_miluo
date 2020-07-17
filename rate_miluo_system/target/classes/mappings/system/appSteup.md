updateSample
===
	
	id=#id#,overrun=#overrun#,operation=#operation#,sos=#sos#,user_id=#userId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(overrun)){
	 and overrun=#overrun#
	@}
	@if(!isEmpty(operation)){
	 and operation=#operation#
	@}
	@if(!isEmpty(sos)){
	 and sos=#sos#
	@}
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	
	
	
updateOverrunOnOff
===
    update api_setup set overrun=#overrun# where user_id=#userId#

updateOperationOnOff
===
    update api_setup set operation=#operation# where user_id=#userId#
	
updateSosOnOff
===
    update api_setup set sos=#sos# where user_id=#userId#
    
getByUserId
===
    select * from api_steup where user_id=#userId#


	

	