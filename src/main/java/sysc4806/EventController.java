package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EventController {
    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/eventsEntry", method = RequestMethod.GET)
    public String newEvent(Model model) {
        model.addAttribute("pageTitle", "New Event");
        model.addAttribute("givenAction", "/eventsEntry");
        model.addAttribute("givenTitle", "");
        model.addAttribute("givenContent", "");
        return "eventsEntry";
    }

    @RequestMapping(value = "/eventsEntry", method = RequestMethod.POST)
    public String addEvent(@RequestParam String title , @RequestParam String content) {
        Event newEvent = new Event(title, content);
        eventRepository.save(newEvent);
        return "redirect:/events";
    }
    @GetMapping(path = "/events")
    public String allEntries(Model model){
        List<Event> content = eventRepository.findAll();
        model.addAttribute("events",content);
        return "event";

    }
//    @GetMapping(path="/deleteEntries")
//    public @ResponseBody
//    String deleteAllPrograms () {
//        eventRepository.deleteAll();
//        return "deleted all";
//    }

    @RequestMapping(value = "/eventsEntry/{id}", method = RequestMethod.GET)
    public String editEvent(@PathVariable(value = "id") Long eventId, Model model) {

        Optional<Event> entry = eventRepository.findById(eventId);
        model.addAttribute("pageTitle", "Edit Entry");
        model.addAttribute("givenAction", "/entry/" + eventId);
        model.addAttribute("givenTitle", entry.get().getTitle());
        model.addAttribute("givenContent", entry.get().getContent());
        return "eventEntry";
    }

    @RequestMapping(value = "/eventsEntry/{id}", method = RequestMethod.POST)
    public String updateEvent(@PathVariable(value = "id") Long eventId,
                              @RequestParam String title,
                              @RequestParam String content) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.get().setTitle(title);
        event.get().setContent(content);
        eventRepository.save(event.get());
        return "redirect:/events";
    }

    @RequestMapping(value = "/eventsEntry/delete/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable(value = "id") Long entryId) {
        eventRepository.deleteById(entryId);
        return "redirect:/events";
    }


}