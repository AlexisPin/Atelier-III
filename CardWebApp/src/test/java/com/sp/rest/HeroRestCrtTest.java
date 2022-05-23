package com.sp.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sp.model.Card;
import com.sp.service.CardService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CardRestCrt.class)


public class HeroRestCrtTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CardService cService;

	Card mockCard= new Card(1,"jdoe", "strong","famille","affinite","imgURL","petiteimage", 100,50,25,2,1000,2);
	
	@Test
	public void retrieveHero() throws Exception {
		Mockito.when(
				cService.getCard(Mockito.anyInt())
				).thenReturn(mockCard);
				

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/card/50").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expectedResult= "{\"id\":1,\"name\":\"jdoe\",\"description\":\"strong\",\"family\":\"famille\",\"affinity\":\"affinite\",\"imgUrl\":\"imgURL\",\"smallImgUrl\":\"petiteimage\",\"energy\":100,\"hp\":50,\"defence\":25,\"attack\":2,\"price\":1000,\"idUser\":2}";


		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	}


}
