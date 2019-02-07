package metube.web.servlets;

import metube.domain.models.view.AllTubesViewModel;
import metube.service.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class TubesAllServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubesAllServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AllTubesViewModel> allTubesViews = this.tubeService
                .findAllTubes()
                .stream()
                .map(tube -> this.modelMapper.map(tube, AllTubesViewModel.class))
                .collect(Collectors.toList());

        req.setAttribute("allTubes", allTubesViews);
        req.getRequestDispatcher("/jsps/all-tubes.jsp").forward(req, resp);
    }
}
