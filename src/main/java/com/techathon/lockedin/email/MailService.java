package com.techathon.lockedin.email;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.itextpdf.io.font.constants.StandardFontFamilies;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
 

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	public String sendMail(EmailRequestDto request, Map<String, String> model) {

		String response;
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			File newPdf =  new File("output.pdf");
			ClassPathResource img1 = new ClassPathResource("templates/footer.jpg");
			ClassPathResource img2 = new ClassPathResource("templates/kudo.jpg");
//			ClassPathResource pdf = new ClassPathResource(newPdf.getAbsolutePath());
			ClassPathResource pdf1 = new ClassPathResource("static/KUDOS Certificate - Spot Recognition.pdf");
			PdfReader reader = new PdfReader(pdf1.getFile());
			PdfWriter writer = new PdfWriter(newPdf.getAbsolutePath());
			PdfDocument pdfDoc = new PdfDocument(reader, writer);
			
			Date date = new Date(); // your date
			// Choose time zone in which you want to interpret your Date
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			 DateFormatSymbols dfs = new DateFormatSymbols();
		     String[] months = dfs.getMonths();
			
		      Document doc = new Document(pdfDoc);
			  doc.showTextAligned(new Paragraph(request.getFrom()).setFont(PdfFontFactory.createFont(StandardFontFamilies.HELVETICA)).setBold().setFontSize(8f) ,getfloatfrompx(90),getfloatfrompx(920-100), 1, TextAlignment.LEFT, VerticalAlignment.MIDDLE ,0);
			  doc.showTextAligned(new Paragraph("TOP 50 Reviewers").setFont(PdfFontFactory.createFont(StandardFontFamilies.HELVETICA)).setBold().setFontSize(8f) ,getfloatfrompx(600),getfloatfrompx(920-300), 1, TextAlignment.LEFT, VerticalAlignment.MIDDLE ,0);
			  doc.showTextAligned(new Paragraph(months[month] + " -- " + year).setFont(PdfFontFactory.createFont(StandardFontFamilies.HELVETICA)).setBold().setFontSize(8f) ,getfloatfrompx(250),getfloatfrompx(920-400), 1, TextAlignment.LEFT, VerticalAlignment.MIDDLE ,0);
			  doc.showTextAligned(new Paragraph("Your Manager").setFont(PdfFontFactory.createFont(StandardFontFamilies.HELVETICA)).setBold().setFontSize(8f) ,getfloatfrompx(1400),getfloatfrompx(920-650), 1, TextAlignment.LEFT, VerticalAlignment.MIDDLE ,0);
			  doc.close();
 
			Template template = configuration.getTemplate("email.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

			helper.setTo(request.getTo());
			helper.setFrom(request.getFrom());
			helper.setSubject(request.getSubject());
			helper.setText(html, true);
			helper.addInline("footer.jpg", img1.getFile());
			helper.addInline("kudo.jpg", img2.getFile());
			helper.addAttachment("attachment.pdf",newPdf);

			mailSender.send(message);
			response = "Email has been sent to :" + request.getTo();
		} catch (MessagingException | IOException | TemplateException e) {
			response = "Email send failure to :" + request.getTo();
		}
		return response;
	}

	private float getfloatfrompx(int a) {
		return (a*72)/300;
	}
}