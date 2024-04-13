package ma.yassine.hopital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.yassine.hopital.entities.Patient;
import ma.yassine.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
        private PatientRepository patientRepository;
        @GetMapping("/user/index")
        public String index(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int p,
                            @RequestParam(name = "size",defaultValue = "20") int s,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword
                            ){
            Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(p, s));
            model.addAttribute("ListPatients",pagePatients.getContent());
            model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
            model.addAttribute("currenPage",p);
            model.addAttribute("keyword",keyword);
            return "patients";
        }
        @GetMapping("/admin/delete")
        @PreAuthorize("hasRole('ROLE ADMIN')")
        public String delete(Long id, String keyword, int page){
            patientRepository.deleteById(id);
            return "redirect:/user/index?page="+page+"&keyword="+keyword;
        }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }



    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ROLE ADMIN')")
    public String fromPatients(Model model){
            model.addAttribute("patient",new Patient());
            return "/formPatients";
    }

    @PostMapping("/admin/savePatient")
    @PreAuthorize("hasRole('ROLE ADMIN')")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
            if (bindingResult.hasErrors()){
                return "formPatients";
            }
            patientRepository.save(patient);
            return "redirect:/user/index?keyword="+patient.getNom();
    }
    @PreAuthorize("hasRole('ROLE ADMIN')")
    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, @RequestParam(name = "id") Long id){
        Patient patient=patientRepository.findById(id).get();
        model.addAttribute("patient",patient);
        return "editPatient";
    }

}
