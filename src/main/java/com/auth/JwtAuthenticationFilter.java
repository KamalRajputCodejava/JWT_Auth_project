package com.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.helper.JwtUtil;
import com.auth.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	 private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService ;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get jwt 
		//check bearer starts 
		//validate this token 
		
		String reqTokenheader  = request.getHeader("Authorization");
		String    username =  null;
		//checking null or format of token 
		if(reqTokenheader !=null&&reqTokenheader.startsWith("Bearer ")){
			  String jwtToken = reqTokenheader.substring(7);
			  try {
				 
				  
				  username = this.jwtUtil.extractUsername(jwtToken);
				  
				  
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			  UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			//security 
			  if(username !=null &&SecurityContextHolder.getContext().getAuthentication()==null) {
				  
				  
				  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				  
				  usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				  
				  
				  
				  
				  
			  }else {
				System.out.println("not validat token ");
			}
               			
			//farworded
			  
			  
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
