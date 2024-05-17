package com.giraffe.jsl.ui.server.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.giraffe.jsl.ui.server.data.Page;
import com.giraffe.jsl.ui.server.dto.DateRange;
import com.giraffe.jsl.ui.server.service.Service;

public abstract class BaseController<T>
{
	@Autowired
	protected Service<T> service;

	private String viewLocation;

	public BaseController(String viewLocation)
	{
		this.viewLocation = viewLocation;
	}

	@PostMapping("/search")
	public String search(@ModelAttribute("dateRange") DateRange dateRange, Model model) throws Exception
	{
		model.addAttribute("dateRange", dateRange);
		return "redirect:/?" + dateRange.getUrl();
	}

	@GetMapping("/add")
	public String showPositionEditForm(Model model) throws Exception
	{
		return showEditForm(model, initDto());
	}

	@GetMapping("/add/{id}")
	public String showNewCourseForm(@PathVariable(value = "id") long id, Model model) throws Exception
	{
		return showEditForm(model, prepareByTemplate(service.getById(id)));
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("course") T entity) throws Exception
	{
		service.save(entity);
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) throws Exception
	{
		model.addAttribute("entity", service.getById(id));
		return viewLocation + "/update";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") long id)
	{
		service.delete(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(
			@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			@RequestParam(name = "from", required = false) LocalDate from,
			@RequestParam(name = "to", required = false) LocalDate to,
			Model model) throws Exception
	{
		final int pageSize = 7;
		final DateRange dateRange = new DateRange(from, to);
		final Page<T> page = service.findPaginated(pageNo, pageSize, sortField, sortDir, dateRange);
		model.addAttribute("dateRange", dateRange);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalItems());
		model.addAttribute("jobContacts", page.getData());
		return "index";
	}

	public String showEditForm(Model model, T entity) throws Exception
	{
		model.addAttribute("entity", entity);
		
		return viewLocation + "/add";
	}

	protected T prepareByTemplate(T template)
	{
		return template;
	}

	abstract protected T initDto();

}
