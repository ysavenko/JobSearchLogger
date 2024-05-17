package com.giraffe.jsl.ui.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.giraffe.jsl.ui.server.dto.Position;

@Controller
@RequestMapping("/position")
public class PositionController extends BaseController<Position>
{

	public PositionController()
	{
		super("position");
	}

	public PositionController(String viewLocation)
	{
		super(viewLocation);
	}

	@Override
	protected Position initDto()
	{
		return new Position();
	}

}
