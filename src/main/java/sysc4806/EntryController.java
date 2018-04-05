package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EntryController {
    @Autowired
    EntryRepository entryRepository;

    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public String newEntry(Model model) {
        model.addAttribute("pageTitle", "New Entry");
        model.addAttribute("givenAction", "/entry");
        model.addAttribute("givenTitle", "");
        model.addAttribute("givenContent", "");
        return "entry";
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public String addEntry(@RequestParam String title ,@RequestParam String content) {
        Entry newEntry = new Entry(title, content);
        entryRepository.save(newEntry);
        return "redirect:/deadlines";
    }
    @GetMapping(path = "/deadlines")
    public String allEntries(Model model){
        List<Entry> content = entryRepository.findAll();
        model.addAttribute("entries",content);
        return "deadline";

    }
    @GetMapping(path="/deleteEntries")
    public @ResponseBody String deleteAllPrograms () {
        entryRepository.deleteAll();
        return "deleted all";
    }

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.GET)
    public String editEntry(@PathVariable(value = "id") Long entryId, Model model) {

        Optional<Entry> entry = entryRepository.findById(entryId);
        model.addAttribute("pageTitle", "Edit Entry");
        model.addAttribute("givenAction", "/entry/" + entryId);
        model.addAttribute("givenTitle", entry.get().getTitle());
        model.addAttribute("givenContent", entry.get().getContent());
        return "entry";
    }

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.POST)
    public String updateEntry(@PathVariable(value = "id") Long entryId,
                              @RequestParam String title,
                              @RequestParam String content) {
        Optional<Entry> entry = entryRepository.findById(entryId);
        entry.get().setTitle(title);
        entry.get().setContent(content);
        entryRepository.save(entry.get());
        return "redirect:/deadlines";
    }

    @RequestMapping(value = "/entry/delete/{id}", method = RequestMethod.GET)
    public String deleteEntry(@PathVariable(value = "id") Long entryId) {
        entryRepository.deleteById(entryId);
        return "redirect:/deadlines";
    }

}