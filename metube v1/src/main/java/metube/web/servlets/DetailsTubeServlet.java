package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeDetailsViewModel;
import metube.service.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/details")
public class DetailsTubeServlet extends HttpServlet {

    private final TubeService tubeService;

    private final ModelMapper modelMapper;

    @Inject
    public DetailsTubeServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeName = req.getQueryString().split("=")[1];
        TubeServiceModel tubeServiceModel = this.tubeService.findByName(tubeName);
        req.setAttribute("tubeViewModel", this.modelMapper.map(tubeServiceModel, TubeDetailsViewModel.class));

        req.getRequestDispatcher("/jsps/details-tube.jsp").forward(req, resp);
    }
}
