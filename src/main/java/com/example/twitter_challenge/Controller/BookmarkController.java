package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Entity.Bookmark;
import com.example.twitter_challenge.Service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        return bookmarkService.getAllBookmarks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Long id) {
        return bookmarkService.getBookmarkById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkService.createBookmark(bookmark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bookmark> updateBookmark(@PathVariable Long id, @RequestBody Bookmark bookmark) {
        Bookmark updatedBookmark = bookmarkService.updateBookmark(id, bookmark);
        if (updatedBookmark != null) {
            return ResponseEntity.ok(updatedBookmark);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        bookmarkService.deleteBookmark(id);
        return ResponseEntity.noContent().build();
    }
}
