 package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entity.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class PageController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}
	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("Home Page Handler");
		model.addAttribute("Name","Substring Technologies");
		model.addAttribute("YoutubeChannel","learn code with vinay");
		model.addAttribute("GitHubRepository","https://github.com/VinaySinghRawat?tab=repositories");
		return"home";
	}
	
	//about route
	@RequestMapping("/about")
	public String aboutPage() {
		System.out.println("about page loading");
		return "about";
	}
	
	//services
	@RequestMapping("/services")
	public String servicesPage() {
		System.out.println("Services page loading");
		return "services";
	}
	@GetMapping("/contact")
	public String contact() {
		return new String("contact");
	}
	@GetMapping("/login")
	public String login() {
		return new String("login");
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		UserForm userForm=new UserForm();
		model.addAttribute("userForm",userForm);
//		userForm.setName("vinay");
//		userForm.setAbout("working at");
		return new String("register");
	}
	
	
	//processing register
	@RequestMapping(value="/do-register",method=RequestMethod.POST)
	public String processRegister( @Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session) {
		System.out.println("working properly");
		//fetch form data
		//userform
		System.out.println(userForm);
		
		
		//validate form data
		if(rBindingResult.hasErrors()) {
			return "register";
		}
		
		//save to database
		//userservice
//		User user=User.builder()
//				.name(userForm.getName())
//				.email(userForm.getEmail())
//				.password(userForm.getPassword())
//				.about(userForm.getAbout())
//				.phoneNumber(userForm.getPhoneNumber())
//				.profilePic("data:image/jpeg;base64,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")
//                 .build();
		User user=new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setAbout(userForm.getAbout());
		User savedUser=userService.saveUser(user);
		user.setPhoneNumber(userForm.getPhoneNumber());
		user.setProfilePic("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIALoAugMBIgACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAABgcBBAUDAgj/xAA8EAABAwMDAgQEAwUHBQEAAAABAAIDBAURBhIhEzEiQVFhBxRxkTIzgRUjQlKxNGJyocHw8SVjc9HhFv/EABkBAQADAQEAAAAAAAAAAAAAAAACAwQBBf/EACQRAAMAAgEDBQEBAQAAAAAAAAABAgMRBBIhMRMUIkFRMkIj/9oADAMBAAIRAxEAPwCjUREAREQBERAFkIF1rBZp7tPsjBEY/E70XG1K2ycQ7ekaFNSzVUgjp43SPPYAKSUXw/1DVAOFK2L/AMjsKx9NafpbXE3owt3kcyEZKnNKWCIYzlYq5bb+Js9rMr5FEO+GmoGgktg9vGef8lpT6Ev8AJdShw/uuX6IMjHcHsEdBE8cAZK57mx6WP8AD8v1tnuFCM1VLJG31I4WiQv0/X2qnniLJI2Fp7gjIKhd90HbarJjhbE7yMYwrJ5a/wBIi+Mq/llJrCkmoNJ1lqLnNHUhHmO6jhaRnI7LTNTS2jNeOoeqRhERSKwiIgCIiAIiIAiIgCIiAIi+sBAelJA+onZEzu44Vraeo4bbSxwxnn+I+pUP0fRsY19bL5cN9h5r1rr9WVcxhtzS0Dz8ysmbeR9KPU40LFHW/LLat8zJHBmcYHZdulie9gLexX5+ptQXi1ThzpX59JBnKl9i+KYoi0VlJJJ67XBUe2exeWWXDHbpJW+HusvoKmF27HC5WmPiHYbowNZU9KV38Eg54U4Y5k8bXtO5rhwr540tee5mrLSfgjMkEwGXMLgfJa8tFNI3JAA9PNSx7WNY4uHA55UI1dqxttgL4I9/TzuwcfRQvjqfLJ48rrwc+62pjonNkZuBHOQqi1lp6OllM9MQAfxNXRvXxKra8lkNOIc8cuyopJPcLvM7a2SV3chuTgLuLFUPf0W1km56X3OORhYW9U26pgbvkheGjucLSwtiezzqly+5hZAycBYRdImSMLCysIAiIgCIiAIiyBlAYX00ZcF8rat0fVrYGYzl4XH4JSt0kTGKhk+QpaCFp3S43FvGApja9M0tKyJjwHnvkjkL5sdvbI/qAfgaAvPU97FpYPzCS7GIzgrzHdU9I9rS1r8JvS6btldDsqaWlkGMDqNBXCvfwssdQXPpGNgcTnMT8N+ygx1ve207aq3QU0cLXYke9vVewZxk57fZdTSNTqPVdTUvlr6aOipmZNRPAI2l5PDeMLROO1PkxXSdnUt/w6htL+vFUPfIPw8DCsnTlQ6GKKCRxAaMEE9yq9sd3rqe6soa6Vk9OXbOpC/e1o559R281Io7gfnH9Dlgdw5UPJU1suvFtaJlX1rGxuG4Y8wVBa6w01yc8SB2yQ9srduUs3S37shwUeuGopaO1tMGTO95bnyaB5k+SjWV3RzHh6VpG3F8M9Psf1pKQHzw9/AXZjsdtooCylip4htxhgGSFE6+K/v0tJeaG40kksZ3TQRM6r2x58R5Pcd+yilt1fqC41ny1OaavhYXOe99N0y1oPBJHbI/qtHpU58lXWpvRMLzZ6aZr4HgEO9lS18oxb7rUUwztjfgZ9FYVv1XFXVTIj1I3ngxv5AI9Copr6LbejKPwyMBB9U4/VNdLLOVKrH1IjJQd0PdMHK2nmHpI7McYDQNoIyBy7knleS6VZG5lkt7j0cSSSkbW+MYLRyfMen6rmoAiIgCIiAL0ha10jQ84aTglea2KKf5edknl2d9EB4uGHED1XU01HvvEOfI5XMmx1X7e244Xb0dt/bALvJhwFC3qWXYVvIkXJaAymo8Hu4LxNu+cqd7omPA7AjKzZB8xK1j+WqcUduY0tLWDcV5US6Z6d5FHk4VFpynkDP+mQkDn8sLafpqKmjAFPBGO4DW5PKlsbHRt2xjBCTsy7kBx8ytnp/Ex+s3Xgg1VbRBAQSWx/yjjd9Vo0E0Uc5iaMgHnC62rHyywtipsF7jhrf9V86asX7zbO7x4ySsjlutI2Kko2z4udez5bGwtAXBpIhPWMdA4N3ZzkcFTu66fY6keA4ZxwoGynfbriyGbqeJ42OA8P6lLioemMVzU9iR0tjZVDEkED89w5mCPsvGr0lRREn9nMbuGHbXHDh9FI6DxMY/s71C6bnhzXNkGSBwVon5SZryNUU/d9OUsEwlipI43DHICiWuKLrUTZmDJh/orzuNvjlZuDAVXusaGFkLwzAa9pB98qqacWtl6ayQ5KPPdfZMexu3dv53E9v0SdnTlew92nC6dDp+tq6u2QiMYuEoZEGyNLsZGSW5yPXkcr1EeS1p6PbUbX09vsdG/Z+7ouscZzmR7nYPHfGFwlKvibcGXDWVcYmhsVPspYwPSNob/UFRVdOBERcAREQBfbBuLWjuThfC+2Ha5rsA4OcHsUBuXm3y2uuNNOQXbWuy3zBGVv6bhkikbXAfuhJ0ifcjK6GtKaKa32G9U8IijrqPa9jQdokjO12D7+nsvG1x11HZOpNTv+QqJA+KTHAe04z/AKKGX+TRxlvIi2dMkdRrh24VjW7BiyTyqy0qSY2EeYyrApJyxnJxwvMxvpo2clbO6zbtxngr5kALHHz55C5Jr9g7/XPmk1x3xhrDj9Vs9wmtGNYXvZx+o5jLpKyJk1WwHpR57+ij+i9TagdcZP2/ZfloWna6ojBwD7jJ+4Xrqqvt1ua+pqJXRygcbHkefZRu061lcyWJ3iiDd7C4E7vrnuVnltd0bajaLM1FqNtLbXvpoHVc5z04I8Zdj3UW07dKjUNNWG8Wd1vEQ/dF+cu+4C5F+1ZRsZG+ho3RThgd34IPfnywuVZtVRV84prhVVEW535Zd4c+mUtul3Qx4+nwWvp93Xo2Fx5Hmuy/AZh3iUbtlZFExgix08cYW8LkCS0pGSVOmUZMbdbPWpa7pk9TyP2Ve6tIfGYyB6/ZTOpqsh3T7e6guqZAInFxOQCVU31UjRgWilbrj9o1O3t1Crct1ujsN0ud9lbJN+wLLTmlEr/CJ3xAYz59+x7bh7KoKjM1W8MG4vkO0epJV5fGClmsWjq5kdQ3ddrlG+SPf2jbGGgAfVjSV60+Dy7/AKZRVRK+aaSWU5fI4vcfc8leSIukQiIgCIiAIiICwbQx+pfhlW2yNhkrrHN85Awd3Qu/M+3Jx7LOmap9XoeroJHOc2GR7mZ5DRgHH3yo1o/UdRpm+U9dC5xiDgKiEHiWPzafVWdVV9JoVtXXUFohuumb80TU72nAp5MEGMnBwOf94UMsdU6RbgyenW2bWkH7KeORwOdvIHmpyJGtjbzjjsVX+kJm/s9u3GdoI5ycKRy1DmbW9wSDnPOF5Fdmz07+T2b9wqwxm4uxjuoXqLVjYmdGjeJJyeGsJyuneql01tfJTua452g54H1WjZrVS2mE3G6bnSY3AluQpxryxrS7HItejrrqKY3G9T7YCeGyOAAb5cD/AJUwpdMaeggFPHvOBjexg/ouU7VPz8/y1NE4U2TtIHB+q9Y5rtHVAw0zXUuAMtHjz+vCuXyO9DN06RskZeairnnc4YA2AYCj12+HtLVsfUWqqd1gMkO8Jz6rt1k91G98FDIJTy0OAIP1Wi+411JStnqKfp1TXeIx52o00+x1S32Iza71dNNTup71HM6EYa2QHIHuVOaC8R10bZYJM5GB3GVzobrQaphkgqYi2ocNrgWLj22hmtVfUU0T90fL28Kq9V9dwu3YnEcxe0+LOFFNVPd038g7jg45/RbdJXOcC1uA4cuGeSuRqCRraORzm9MDlpJ59yoQn1IlrW2QjQdofeNcW2ia3IFUHyDIGGtOT/Rdf4y6ni1Hq2RlI4PpKAGCN4/jOfEfpnj9F1fhXSus1o1BrSriAjpqYxUbpHAiSRxO4Yzn+UeWdyq5zi5znHGScr2UeI/J8oiIcCIiAIiIAiIgCsb4bavt9HQVGldTRdWzV8nEhP8AZ3nAz7Dsc+R5Vcr6yUBblHE2yVtXQRTif5eTZHNw3c3jB+xXYfWtEewudjyLvPHKrLSlyJldTVEjnb8bS4k9vJSuSp/dbmuLXjl2c4H/ALXm5o1R6+JqoTO5cZ2x2mXLWuD3BwGOx4x/wpZbaH5qliBk6jOmA7jucKpKm5Smne1pcMeIeLnPqVKNK6sq5KVjXbzg8lo3c47FQcNSK/ETGosNFA8SdLB/maMLyk1BQWaLpVAmcc9xHnC6NLcfmIxI2RhGOW7uR9QuLe3fONdGxjOTw57c4K5NOQm/sxTa9tM0oiYarc7yMH4R9V7SU0F1e4nqOY7y9fdQ630dXabvI98YkheMOjdjLf0/X/JT+grqaSnDodsXh/DjGPZdyU2S058GtFZIaSPMTdgbzwFGa+fZemdXDWTwElpHZwx5ru3jUBpdzWkknnJPBx6FVbVX6StrJHTkDphxAz+I4wOVyIdHE39kqoixg6zh4HHw7eeT/Rci+me6Stt1HHvqql/RbGPU+f25XNhuk8UYe1xAd2A/hUr0RV01ogu2s7rGZIaNvQpQSMyTEDIHvyOfLJV2HE+sZrUQ2ePxSqKbSOlLZoi3tglc9nWrHu5cDkEH6k5OfZU8utqm8Pv9/rbo9jozUSbhG6Qv2DyGSuSvQPHCIiAIiIAiIgCIiALKwiA2beHOrImscWlzgMjyUuqpau2FkNYx3TLSGSN4a4H3UUtIzcqcf9wK3JaOOvpGwyNDm7RwQsvIpTS2elw53DK7rKrsSMBwwHNxn9Vmy3iW3ue6N24k+HnjPrhel+sc1trdrfyncxk8/ouLJDLCRI5gIPp5KSU1PY7kdS96LHtuopKiTEMgZhgLnA8ZPufquhTaj6crupsMcchOd2CTjjlVYy4PjYGsOMnJHqhuUhbgOI5JVb47bHrQWvcbzROLpxI1mGN2k87s91HqrULqM9aB+Yy3AGfM4z/v3UFNdK4kOe4gnsSvn5t4AaTkDkA9srq4+vJz3Eoktz1M+eFzI3bXOzkkZH6eij0VR4nlxyX9wPNa43Su8IySunZ7LNcagMacRDkvwremYRDqq32Ec7xGY6fc7GSfb3+y8bne6uuoqWgJEVJTNy2FmQHvP4pHerj/AKKZVVuhoaCaKCMN8GM+Z+qrl/DiEw0q3ocyXKnZg91hEVxgCIiAIiIAiIgCIiALdhtVdPQTV8NLK+kgcGyzNblrCfUrSW7T3SupqKoooKmRlNUfnRN7P+qAzZh/1On/AMYV02YCSMHGcBVRp+3kyRVL+BuGFb2n4x0gD3wsHKrbR63Fhxi2zX1Dam1dE4FucDI47FQ5tCO8kZBHZrcYKtSspy6mdgfwqG/JfvnODR35B81mmmkXp9S0Rpuko6tznCLp55wDhfP/AOGaWYDntfjgFWTZKZplDX5a44x64XbfaDHueYmuLnc5HdWLLf0U0oXlFLu0O7pAt3bgCSF5M0htwXDuPMq4a6hbHzjGRyAo9W0/hcQ3HkE9e/07MY39EEisjDKIWNY0E4dt7qaWq0MoYcsYG7vZfNkod1UXOw7lSWSHbH27BV5LbLu09kQy+RjY/juCqlqW7Z5B6OKuK+s8D1UVyZsrJR/eK18R+TLzluEzUREW08sIiIAiIgCIiAIiIAsjusLI7oCdUMTW2mjcOc4Vj2E/uWHzwq403IKuyRxA5fC/G328lY9iG2OMLy8/9NHtqt40SjZvg+oUWrqV0NU4443cKXUQ8K0rtSbnhzWj3VWuxTF6rRyICY2Nlb+JvYqaW+pbVW5s+fEBh31UapaMujwBnBWYqKph3xsc5sbzktyuzXSdtKke873VVS9wADFxbsNo2nHHkFIW0xjhwR2C4VfG6Soa3yKiyUNbM6epNrC/B5K6dYza0k+i2LbA2OnHC8q/xgj0XDjrdEK1A3DM+qqC8/26X/Erk1EA2F5d2A5+ipa4yieslkb+EuOFt4f2Q5lf80jVREW48sIiIAiIgCIiAIiIAsrCygOzpi7G13Fr3/kP8MgPp6q6rQ5r4o5InB8buQ4divz4D7KZ6G1i6zyso6/LqJx/F3Mf/wAWXkYetbRrwZ9LpoviifkDBW5VxiWEHsQuVaqqGrhZPTyskjfy1zDkFdXkHDuxWNJ6La87Rp253TqnRyj9V3mwRPAOPuuPJCHuBP4gvUOlYPAeylD6fo5a33Nqv6MMRJIB7AKMNY6prS4DwgrfqGSzOBld59l60lP0mF5457KFvqfYnHxR9vxHGAOFoVWcnGD5r6udzordTOqrjUxxRD+Y91Ues/iRLWh9HZAYoDlrpzw5/wBPRSjC7Zx5FPdn18R9Sw7pLdQSB7ycTPae3sq2JWXOLjk8kr5XpY4ULSMeXI8j7hERTKgiIgCIiAIiIAiIgCIiALOVhEB29P6nuthkDrfUuazOXRO5af0Vn2H4w0MgZFe6OWB3nLF42/buqW8kCjWOWTnJSP0cdf6akDXw3Jhz5EYP2Xw7XdlHJro9v1X508lnKofGn9LVmX4XtX/E2wQHwySTkcgRMz/mo1e/i9VTRmG0UYgBH5kpyfsFVuSgPClPHhEazUzdud1rrrUGe4VMk7z/ADHgfQLSKLCv8eCpvZlYWVhDgREQBERAEREB/9k=");

		System.out.println("user saved");
		
		//message="registeration successful"
		//adda the message
		Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
		session.setAttribute("message",message);
		//redirect login page
		return "redirect:/register";
	}
	
}
