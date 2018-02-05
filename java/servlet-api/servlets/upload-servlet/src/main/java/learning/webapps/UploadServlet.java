package learning.webapps;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UploadServlet extends HttpServlet {

    final static Integer FILE_SIZE_MAX = 500000;//500kb
    final static Integer MEMORY_THRESHOLD = 50000;//50kb

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + "/pages/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final boolean multipartContent = ServletFileUpload.isMultipartContent(req);
        if (!multipartContent) {
            throw new ServletException("Should send content as multipart/form-data");
        }

        final String temporaryDirectory = System.getProperty("java.io.tmpdir");
        System.out.println("Uploading to system temporary directory: " + temporaryDirectory);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(MEMORY_THRESHOLD);
        diskFileItemFactory.setRepository(new File(temporaryDirectory));
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(FILE_SIZE_MAX);
        List<ItemInfo> items = getUploadItemsFromSession(req);
        try {
            final ItemInfo itemInfo = new ItemInfo();
            final List<FileItem> fileItems = upload.parseRequest(req);
            final Iterator iter = fileItems.iterator();
            while (iter.hasNext()) {
                FileItem fileItem = (FileItem) iter.next();
                //We have to distinguish between between standard form fields and file field.
                if (fileItem.isFormField()) {
                    itemInfo.setDescription(fileItem.getString());
                } else {  //non form field (file)
                    itemInfo.setContentType(fileItem.getContentType());
                    itemInfo.setFileName(fileItem.getName());
                    itemInfo.setUploadToMemory(fileItem.isInMemory());
                    itemInfo.setFileSize(fileItem.getSize());
                }
            }
            items.add(itemInfo);
            storeUploadItemsInSession(req, items);
        } catch (FileUploadException exc) {
            exc.printStackTrace();
            throw new ServletException(exc);
        }
        resp.sendRedirect(req.getContextPath() + "/upload");
    }

    private List<ItemInfo> getUploadItemsFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.isNew()) {
            session.setAttribute("uploaded-items", new LinkedList<ItemInfo>());
        }
        return (List<ItemInfo>) session.getAttribute("uploaded-items");
    }

    private void storeUploadItemsInSession(HttpServletRequest req, List<ItemInfo> items) {
        HttpSession session = req.getSession();
        session.setAttribute("uploaded-items", items);
    }
}
