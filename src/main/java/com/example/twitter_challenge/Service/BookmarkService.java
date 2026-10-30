package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Bookmark;
import com.example.twitter_challenge.Repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll();
    }

    public Optional<Bookmark> getBookmarkById(Long id) {
        return bookmarkRepository.findById(id);
    }

    public Bookmark createBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public Bookmark updateBookmark(Long id, Bookmark bookmark) {
        if (bookmarkRepository.existsById(id)) {
            bookmark.setId(id);
            return bookmarkRepository.save(bookmark);
        }
        return null;
    }

    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }
}
