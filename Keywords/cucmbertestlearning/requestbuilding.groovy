package cucmbertestlearning
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder as RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable


public class requestbuilding {


	@Keyword
	public RestRequestObjectBuilder setme(RestRequestObjectBuilder builder, String string){
		if(string=="post") {
			return builder.withRestRequestMethod("POST")
		}else if(string=="get"){

			return builder.withRestRequestMethod("GET")
		}else if(string=="put"){

			return builder.withRestRequestMethod("PUT")
		}else{

			return builder.withRestRequestMethod("DELETE")
		}
	}

	@Keyword
	public RestRequestObjectBuilder requewithurl(RestRequestObjectBuilder builder, String URL){

		return builder.withRestUrl(URL)
	}

	@Keyword
	public RestRequestObjectBuilder requewithurlpara(RestRequestObjectBuilder builder, String URL,int id){


		URL=URL+id;
		//print(URL)
		return builder.withRestUrl(URL)
	}

	@Keyword
	public RestRequestObjectBuilder httpheaderset(RestRequestObjectBuilder builder) {
		return builder.withHttpHeaders([
			new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
		])
	}
	@Keyword
	public RestRequestObjectBuilder stbody(RestRequestObjectBuilder builder,String innerBodyContentMap){

		return builder.withTextBodyContent(innerBodyContentMap);
	}
	@Keyword
	public RequestObject sendresq(RestRequestObjectBuilder builder){

		RequestObject r = builder.build()
		return r
	}
}