package com.sp.rest;
import java.util.ArrayList;

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

import com.sp.model.User;
import com.sp.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserRestCrt.class)
public class UserRestCrtTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService uService;

	User mockUser=new User(1,"user1","123",1000,"M","P","user1@user1.com",new ArrayList<Integer>());
	
	@Test
	public void retrieveHero() throws Exception {
		Mockito.when(
				uService.getUser(Mockito.anyInt())
				).thenReturn(mockUser);
				

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expectedResult="{\"login\":user1,\"pwd\":\"123\",\"account\":1000,\"lastName\":M,\"surName\":\"P\",\"email\":\"user1@user1.com\",\"cardList\":[],\"id\":1}";

		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	}

}
