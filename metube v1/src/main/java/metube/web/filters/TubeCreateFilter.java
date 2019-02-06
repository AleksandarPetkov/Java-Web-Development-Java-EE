package metube.web.filters;

import metube.domain.models.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")){
            TubeCreateBindingModel model = new TubeCreateBindingModel();
            model.setName(req.getParameter("name"));
            model.setDescription(req.getParameter("description"));
            model.setYouTubeLink(req.getParameter("youTubeLink"));
            model.setUploader(req.getParameter("uploader"));

            req.setAttribute("tubeCreateBindingModel", model);
        }

        filterChain.doFilter(req, resp);
    }
}
