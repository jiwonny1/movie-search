package dev.moviesearch.app.memo.service.impl;

import dev.moviesearch.app.memo.domain.MemoDto;
import dev.moviesearch.app.memo.service.MemoService;
import dev.moviesearch.app.memo.mapper.MemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public List<MemoDto> findMemoList() {
        log.info("===============================================================");
        log.info("로그 출력을 확인 합니다.");
        log.info("===============================================================");
        return memoMapper.selectMemoList();
    }

    @Override
    public MemoDto findMemo(int idx) {
        return memoMapper.selectMemo(idx);
    }

    @Override
    public void insertMemo(MemoDto req) {
        memoMapper.insertMemo(req);
    }

    @Override
    public void updateMemo(MemoDto req) {
        memoMapper.updateMemo(req);
    }

    @Override
    public void deleteMemo(int idx) {
        memoMapper.deleteMemo(idx);
    }
}
