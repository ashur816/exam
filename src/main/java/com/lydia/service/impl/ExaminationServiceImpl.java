package com.lydia.service.impl;

import com.lydia.dao.mapper.AnswerMapper;
import com.lydia.dao.mapper.ExaminationMapper;
import com.lydia.dto.UserAnswer;
import com.lydia.po.Examination;
import com.lydia.service.IExaminationService;
import com.lydia.utils.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Lydia
 * @ClassName: ExaminationServiceImpl
 * @Description:
 * @date 2016/8/30
 */
@Service
public class ExaminationServiceImpl implements IExaminationService {

    @Resource
    private ExaminationMapper examinationMapper;

    @Resource
    private AnswerMapper answerMapper;

    @Override
    public void insertExam(String examQuestion, String examScore, String referenceAnswer, int examLevel) {
        int examId = Integer.parseInt(RandomUtils.generateNumString(8));
        Set set = examinationMapper.allExamId();
        if (set.contains(examId)) {
            examId = Integer.parseInt(RandomUtils.generateNumString(8));
        }
        examinationMapper.insertExam(examId, examQuestion, examScore, referenceAnswer, examLevel);
    }

    @Override
    public UserAnswer getExamAndAnswer(int userId, int examId) {
        return examinationMapper.getExamAndAnswer(userId, examId);
    }

    @Override
    public void updateExam(int examId, String examQuestion, String examScore, String referenceAnswer, int examLevel) {
        examinationMapper.updateExam(examId, examQuestion, examScore, referenceAnswer, examLevel);
    }

    @Override
    public void deleteExam(int examId) {
        examinationMapper.deleteExam(examId);
    }

    @Override
    public List<Integer> getExamIdList(int userId) {
        List<Integer> examIdList = examinationMapper.getExamIdList(userId);
        Iterator it = examIdList.iterator();
        while (it.hasNext()) {
            Object examinationId = it.next();
            //删除原有的答题记录
            answerMapper.deleteAnswer(userId, Integer.parseInt(examinationId.toString()));
            //增加新的答题记录
            answerMapper.insertAnswer("", Integer.parseInt(String.valueOf(examinationId)), userId);
        }
        return examinationMapper.getExamIdList(userId);
    }

    @Override
    public List<Examination> getExamList(int examLevel) {
        return examinationMapper.getExamList(examLevel);
    }

    @Override
    public Examination getExamInfo(int examId) {
        return examinationMapper.getExamInfo(examId);
    }

}
