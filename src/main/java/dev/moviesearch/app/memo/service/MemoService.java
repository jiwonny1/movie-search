package dev.moviesearch.app.memo.service;

import dev.moviesearch.app.memo.domain.MemoDto;

import java.util.List;

public interface MemoService {
    List<MemoDto> findMemoList();

    MemoDto findMemo(int idx);

    void deleteMemo(int idx);

    void updateMemo(MemoDto req);

    void insertMemo(MemoDto req);
}
