package com.giraffe.jsl.ui.server.controller;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.giraffe.jsl.ui.server.dto.JobContact;
import com.giraffe.jsl.ui.server.service.JobContactService;
import com.giraffe.jsl.ui.server.service.PositionService;

@Controller
@RequestMapping({ "/", "/jobcontact" })
public class JobContactController extends BaseController<JobContact>
{

	public JobContactController()
	{
		super("jobcontact");
	}

	public JobContactController(String viewLocation)
	{
		super(viewLocation);
	}

	@Autowired
	private PositionService positionService;

	@GetMapping("/")
	public String viewHomePage(
			@RequestParam(name = "from", required = false) LocalDate from,
			@RequestParam(name = "to", required = false) LocalDate to,
			Model model) throws Exception
	{
		return findPaginated(1, "date", "asc", from, to, model);
	}

	@Override
	public String showEditForm(Model model, JobContact jobContact) throws Exception
	{
		jobContact.setDate(LocalDate.now());
		model.addAttribute("types", ((JobContactService) service).getTypes());
		model.addAttribute("positions", positionService.getAll());
		model.addAttribute("companies", Arrays.asList("BAE Systems", "Fidelity", "Bank of America", "TCS"));
		return super.showEditForm(model, jobContact);
	}

	@Override
	protected JobContact initDto()
	{
		return new JobContact();
	}

	@Override
	protected JobContact prepareByTemplate(JobContact template)
	{
		template.setId(null);
		template.setType(null);
		template.setDescription(null);
		return template;
	}

}
